var vue = new Vue({
    el: '#app',
    data: {
        menuList: [],
        classifyList: [],
        food: {
            name: null,//菜品名
            image: null,//图片
            price: null,//单价
            info: null,//商品简介
            order: null,//排序
            stock: null,//y库存
            taste: [],//口味
            tasteList: null,
            practice: [],//做法
            practiceList: null,
            menuClassifyId: null,
            weight: [],//重量
            weightList: null
        }
    },
    methods: {
        submit: function () {
            var that_food = this.food;
            that_food.tasteList = JSON.stringify(that_food.taste);
            that_food.practiceList = JSON.stringify(that_food.practice);
            that_food.weightList = JSON.stringify(that_food.weight);
            console.log(that_food);
            $.ajax({
                url: "http://118.24.52.233/food/create",
                data: that_food,
                type: "POST",
                success: function (res) {
                    console.log(res);
                    if (res.code == 200) {
                        alert("添加成功");
                    } else {
                        alert(res.msg);
                    }
                },
            })
        },
        addLineWeight: function () {
            this.food.weight.push({
                price: null,
                weight: null
            });
        },
        minusLineWeight: function () {
            this.food.weight.splice(this.food.weight.length - 1, 1);
        },
        addLineTaste: function () {
            this.food.taste.push({
                name: null
            });
        },
        minusLineTaste: function () {
            this.food.taste.splice(this.food.taste.length - 1, 1);
        },
        addLinePractice: function () {
            this.food.practice.push({
                name: null
            });
        },
        minusLinePractice: function () {
            this.food.practice.splice(this.food.practice.length - 1, 1);
        }
    }
})

$(document).ready(function () {
    init();
});

//图片上传
function uploadimg() {
    var fileObj = document.getElementById("doc-form-file").files[0];
    var formFile = new FormData();
    formFile.append("file", fileObj); //加入文件对象
    var data = formFile;
    $.ajax({
        url: "http://118.24.52.233/file/upload/a/multipart/file",
        data: data,
        type: "POST",
        dataType: "json",
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        success: function (res) {
            console.log(res);
            if (res.code == 200) {
                vue.food.image = res.data;
            }
        },
    })
}

//初始化
function init() {
    getMenuList();
    getClassifyList();
}

//获取菜品分类列表
function getClassifyList() {
    $.ajax({
        url: "http://118.24.52.233/menu/classify/select/all",
        type: 'GET',
        data: {},
    }).done(function (res) {
        console.log(res);
        if (res.code == 200) {
            vue.classifyList = res.data;
        } else {
            alert(res.msg);
        }
    }).fail(function (res) {

    });
}

//获得菜单列表
function getMenuList() {
    $.ajax({
        url: "http://118.24.52.233/sys/menu/first/select/all",
        type: 'GET',
        data: {},
    }).done(function (res) {
        console.log(res);
        if (res.code == 200) {
            vue.menuList = res.data;
        } else {
            alert(res.msg);
        }
    }).fail(function (res) {

    });
}