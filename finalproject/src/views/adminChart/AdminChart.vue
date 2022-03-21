<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-pie-chart"></i> schart图表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="c1">
            <el-card shadow="hover"  style="height:500px; width: 620px; margin-left: 250px">
                <div class="schart-box">
                    <div class="content-title">柱状图</div>
                    <schart class="schart" canvasId="bar" :options="options1"></schart>
                </div>
            </el-card>
            </div>

            <div class="c2">
            <el-card shadow="hover"  style="height:500px; width: 620px">
            <div class="schart-box">
                <div class="content-title">饼状图</div>
                <schart class="schart" canvasId="pie" :options="options3"></schart>
            </div>
            </el-card>

            <el-card shadow="hover"  style="height:500px; width: 620px">
            <div class="schart-box">
                <div class="content-title">环形图</div>
                <schart class="schart" canvasId="ring" :options="options4"></schart>
            </div>
            </el-card>
            </div>
        </div>
    </div>
</template>

<script>
    import Schart from "vue-schart";
    export default {
        name: "AdminChart",
        components: {
            Schart,
        },
        data(){
            return{
                options1 :{
                    type: "bar",
                    title: {
                        text: "文章种类-数量柱状图",
                    },
                    bgColor: "#fbfbfb",
                    labels: [],
                    datasets: [
                        {
                            label: "博客",
                            fillColor: "rgba(241, 49, 74, 0.5)",
                            data: [1],
                        },
                        {
                            label: "帖子",
                            fillColor: "rgba(125, 200, 80, 0.5)",
                            data: [1]
                        }
                    ],
                },
                options3 : {
                    type: "pie",
                    title: {
                        text: "各类文章类型比重",
                    },
                    legend: {
                        position: "left",
                    },
                    bgColor: "#fbfbfb",
                    labels: [],
                    datasets: [
                        {
                            data: [1],
                        },
                    ],
                },
                options4 : {
                    type: "ring",
                    title: {
                        text: "用户男女占比",
                    },
                    showValue: false,
                    legend: {
                        position: "bottom",
                        bottom: 40,
                    },
                    bgColor: "#fbfbfb",
                    labels: ["男","女"],
                    datasets: [
                        {
                            data: [1],
                        },
                    ],
                }
            }
        },
        methods:{
            getTypeCntP(){
                this.$axios.post("/chart/typecntP",).then(rst=>{
                    console.log(rst);
                    this.options1.datasets[0].data.pop();
                    for(var i = 0; i < rst.data.length; i++){
                        if(rst.data[i].labelId === "1")
                        {
                            rst.data[i].labelId = "学习";
                        }else if(rst.data[i].labelId === "2"){
                            rst.data[i].labelId = "生活";
                        }else if(rst.data[i].labelId === "3"){
                            rst.data[i].labelId = "科技";
                        }else if(rst.data[i].labelId === "4"){
                            rst.data[i].labelId = "游戏";
                        }
                        //console.log(rst.data[i].labelId);
                         this.options1.labels.push(rst.data[i].labelId);
                         this.options1.datasets[0].data.push(rst.data[i].cnt);
                    }

                }).catch(ex=>{
                    console.log(ex);
                });
            },
            getTypeCntB(){
                this.$axios.post("/chart/typecntB",).then(rst=>{
                    console.log(rst);
                    this.options1.datasets[1].data.pop();
                    for(var i = 0; i < rst.data.length; i++){
                        if(rst.data[i].labelId === "1")
                        {
                            rst.data[i].labelId = "学习";
                        }else if(rst.data[i].labelId === "2"){
                            rst.data[i].labelId = "生活";
                        }else if(rst.data[i].labelId === "3"){
                            rst.data[i].labelId = "科技";
                        }else if(rst.data[i].labelId === "4"){
                            rst.data[i].labelId = "游戏";
                        }
                        this.options1.datasets[1].data.push(rst.data[i].cnt);
                    }
                }).catch(ex=>{
                    console.log(ex);
                });
            },
            getTypeCnt(){
                this.$axios.post("/chart/typecnt",).then(rst=>{

                    this.options3.datasets[0].data.pop();
                    for(var i = 0; i < rst.data.length; i++){
                        if(rst.data[i].labelId === "1")
                        {
                            rst.data[i].labelId = "学习";
                        }else if(rst.data[i].labelId === "2"){
                            rst.data[i].labelId = "生活";
                        }else if(rst.data[i].labelId === "3"){
                            rst.data[i].labelId = "科技";
                        }else if(rst.data[i].labelId === "4"){
                            rst.data[i].labelId = "游戏";
                        }
                        this.options3.labels.push(rst.data[i].labelId);
                        this.options3.datasets[0].data.push(rst.data[i].cnt);
                    }
                }).catch(ex=>{
                    console.log(ex);
                });
            },
            getSexCnt(){
                this.$axios.post("/chart/sexCnt",).then(rst=>{
                    console.log(rst);
                    this.options4.datasets[0].data.pop();
                    for(var i = 0; i < rst.data.length; i++){
                        this.options4.datasets[0].data.push(rst.data[i].sex);
                    }
                }).catch(ex=>{
                    console.log(ex);
                });
            }
        },
        created() {
            this.getTypeCntP();
            this.getTypeCntB()
            this.getTypeCnt();
            this.getSexCnt();
        },

    };
</script>

<style scoped>
    .c1{
        display: flex;

    }
    .c2{
        display: flex;
    }
    .schart-box {
        display:inline-block;
        margin: 0px;
    }
    .schart {
        width: 580px;
        height: 400px;
    }
    .content-title {
        clear: both;
        font-weight: 400;
        line-height: 50px;
        margin: 10px 0;
        font-size: 22px;
        color: #1f2f3d;
    }
</style>