let gridView;
let dataProvider;

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
        width: 50,
        fieldName: "number",
        name: "number"
    },
    {
        header: {
            text: "작성자",
        },
        width: 50,
        fieldName: "writer",
        name: "writer"
    },
    {
        header: {
            text: "제목",
        },
        width: 50,
        fieldName: "title",
        name: "title"
    },
    {
        header: {
            text: "내용",
        },
        width: 100,
        fieldName: "content",
        name: "content"
    }
];

const data = [];

(async () => {
    await fetch("/content/contents")
        .then(res => res.json())
        .then(contents => {
            [...contents].reduce((item, { contentId, contentTitle, contentWriter, contentContent }) => {
                item.push([contentId, contentTitle, contentWriter, contentContent]);

                return item;
            }, []).forEach(content => data.push(content));
        });

    dataProvider = new RealGridJS.LocalDataProvider();
    dataProvider.setFields(fields);
    dataProvider.setRows(data);
    gridView = new RealGridJS.GridView("realgrid");
    gridView.setColumns(columns);
    gridView.setDataSource(dataProvider);
})();