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
let current = 0;
let total = 0;
let searchIndex = -1;
let column = "number";

(async () => {
    await fetch("/content/contents")
        .then(res => res.json())
        .then(contents => {
            [...contents].reduce((item, { contentId, contentTitle, contentWriter, contentContent }) => {
                item.push([contentId, contentTitle, contentWriter, contentContent]);

                return item;
            }, []).forEach(content => data.push(content));
        });

    dataProvider.setFields(fields);
    dataProvider.setRows(data);

    gridView.setColumns(columns);
    gridView.setDataSource(dataProvider);
    gridView.setPaging(true, 10);
    total = gridView.getPageCount() - 1;
})();

const $prev = document.querySelector("#prev");
$prev.addEventListener("click", () => {
    gridView.setPage(current = current - 1 < 0 ? 0 : current - 1);

    searchIndex = -1;
    gridView.setCurrent({
        itemIndex: searchIndex,
        column: "number",
    });
});

const $next = document.querySelector("#next");
$next.addEventListener("click", () => {
    gridView.setPage(current = current + 1 > total ? total : current + 1);

    searchIndex = -1;
    gridView.setCurrent({
        itemIndex: searchIndex,
        column: "number",
    });
});

const $searchColumn = document.querySelector("#search-column");
$searchColumn.addEventListener("change", ({ target: { value } }) => {
    column = value;
});

const $search = document.querySelector("#search-input");
$search.addEventListener("keyup", ({ target: { value }, key }) => {
    if(key !== "Enter") return;

    searchIndex = gridView.searchItem({
        fields: [column],
        values: [value],
        startIndex: searchIndex + 1,
        partialMatch: column === "content",
    });

    if(searchIndex < 0) return;

    gridView.setCurrent({
        itemIndex: searchIndex,
        column: column,
    });
});