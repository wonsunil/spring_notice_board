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
let check = false;
let current = 0;
let limit = 10;
let total = 0;
let searchIndex = -1;
let column = "number";

(async () => {
    await $.getJSON("/content/contents", contents => {
        [...contents].reduce((item, { contentId, contentTitle, contentWriter, contentContent }) => {
            item.push([contentId, contentTitle, contentWriter, contentContent]);

            return item;
        }, []).forEach(content => data.push(content));
    });

    dataProvider.setFields(fields);
    dataProvider.setRows(data);

    gridView.setColumns(columns);
    gridView.setDataSource(dataProvider);
    gridView.setPaging(true, limit);
    total = gridView.getPageCount() - 1;
})();

const $prev = $("#prev");
$prev.click(() => {
    gridView.setPage(current = current - 1 < 0 ? 0 : current - 1);

    searchIndex = -1;
    gridView.setCurrent({
        itemIndex: searchIndex,
        column: "number",
    });
});

const $next = $("#next");
$next.click(() => {
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
column = $searchColumn?.value;

$("#realgrid").click(() => $searchColumn.value = column = gridView.getCurrent().column);

$searchColumn.change(({ target: { value } }) => {
    column = value;
    searchIndex = -1;
    gridView.setCurrent({
        itemIndex: searchIndex,
        column: column,
        dataRow: 0,
    });
});

$search.keyup(({ target: { value }, key }) => {
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