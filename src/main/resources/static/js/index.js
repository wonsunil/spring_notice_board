const gridView = new RealGridJS.GridView("realgrid");
const dataProvider = new RealGridJS.LocalDataProvider();

gridView.setStateBar({ visible: false });
gridView.setCheckBar({ visible: false });

RealGridJS.setRootContext("/js");

const fields = [
    {
        fieldName: "number"
    },
    {
        fieldName: "writer"
    },
    {
        fieldName: "title"
    },
    {
        fieldName: "content"
    },
];

const columns = [
    {
        header:{
            text: "번호",
        },
        width: 100,
        fieldName: "number",
        name: "number"
    },
    {
        header: {
            text: "작성자",
        },
        width: 100,
        fieldName: "writer",
        name: "writer"
    },
    {
        header: {
            text: "제목",
        },
        width: 100,
        fieldName: "title",
        name: "title"
    },
    {
        header: {
            text: "내용",
        },
        width: 280,
        fieldName: "content",
        name: "content"
    }
];

const data = [];
const series = [];
let check = false;
let current = 0;
let limit = 10;
let total = 0;
let searchIndex = -1;
let column = "number";

(async () => {
    await $.getJSON("/content/contents", contents => {
        [...contents].reduce((item, { contentId, contentTitle, contentWriter, contentContent, viewCount }) => {
            item.push([contentId, contentTitle, contentWriter, contentContent, viewCount]);

            return item;
        }, []).forEach(content => data.push(content));
    });

    dataProvider.setFields(fields);
    dataProvider.setRows(data);

    gridView.setColumns(columns);
    gridView.setDataSource(dataProvider);
    gridView.setPaging(true, limit);
    total = gridView.getPageCount() - 1;

    Highcharts.chart('container', {
        title: { text: '조회수별 그래프' },
        yAxis: { title: { text: '' } },
        xAxis: {},
        legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle' },
        series: data.map(content => { return { name: content[1], data: [0, content[4] === null ? 0 : content[4]] } }),
        responsive: {
            rules: [{
                condition: { maxWidth: 500 },
                chartOptions: {
                    legend: {
                        layout: 'horizontal',
                        align: 'center',
                        verticalAlign: 'bottom'
                    }
                }
            }]
        }
    });
})();

const $prev = $("#prev");
$prev.on("click", () => {
    gridView.setPage(current = current - 1 < 0 ? 0 : current - 1);

    searchIndex = -1;
    gridView.setCurrent({
        itemIndex: searchIndex,
        column: "number",
    });
});

const $next = $("#next");
$next.on("click", () => {
    gridView.setPage(current = current + 1 > total ? total : current + 1);

    searchIndex = -1;
    gridView.setCurrent({
        itemIndex: searchIndex,
        column: "number",
    });
});

const searchItem = (field, value, startIndex, partialMatch) => gridView.searchItem({ fields: [field], values: [value], startIndex: startIndex, partialMatch: partialMatch });
const getColumnIndex = columnName => {
    let index = 0;

    for(let i = 0; i < fields.length; i++) {
        if(fields[i].fieldName !== columnName) index++;
        if(fields[i].fieldName === columnName) break;
    }

    return index;
};
const getPage = () => gridView.getPage();
const setPage = page => gridView.setPage(page);
const setCurrent = (index, column) => gridView.setCurrent({ itemIndex: searchIndex, column: column });
const getLastIndex = text => {
    let index = -1;

    for(let i = 0; i < 10; i++) {
        const searchedIndex = gridView.searchItem({
            fields: [column],
            values: [text],
            startIndex: searchIndex + 1,
            wrap: false,
            partialMatch: column !== "number",
        });

        if(searchedIndex !== -1) index++;
        if(searchedIndex === -1) break;
    }

    return index;
};

const $search = $("#search-input");
const $searchColumn = $("#search-column");
column = $searchColumn?.val();

$("#realgrid").on("click", () => $searchColumn.val(column = gridView.getCurrent().column));

$searchColumn.on("change", ({ target: { value } }) => {
    column = value;
    searchIndex = -1;
    gridView.setCurrent({
        itemIndex: searchIndex,
        column: column,
        dataRow: 0,
    });
});

$search.on("keyup", ({ target: { value }, key }) => {
    if(key !== "Enter") return;

    searchIndex = searchItem(column, value, searchIndex + 1, column !== "number");

    if(getLastIndex(value) !== -1 && check) {
        let index = -1;
        const columnIndex = getColumnIndex(column);

        for(let i = 10, limit = data.length; i < limit; i++) {
            const item = data[i];

            if(item[columnIndex].indexOf(value) !== -1) {
                index = i;

                break;
            };
        }

        setPage(Math.floor(index / 10));
        setCurrent(searchItem(column, value, searchIndex + 1, column !== "number"));

        check = !check;

        return;
    };

    if(getLastIndex(value) === -1 && !check) {
        if(getPage() === total) {
            setPage(0);

            searchIndex = searchItem(column, value, searchIndex + 1, column !== "number"), column;
            setCurrent(searchIndex);

            return;
        };

        check = true;
    };

    if(searchIndex < 0) return;

    setCurrent(searchIndex, column);
});