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
        width: 400,
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

    dataProvider.setFields(fields);
    dataProvider.setRows(data);

    gridView.setColumns(columns);
    gridView.setDataSource(dataProvider);
})();