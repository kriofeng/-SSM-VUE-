<template>
    <div id="show_content">
        <div id="show_table">
            <el-table
                    :data="followers">
                <el-table-column
                        label="头像">
                    <template #default="scope">
                        <el-avatar :src="head[scope.$index]"></el-avatar>
                    </template>
                </el-table-column>
                <el-table-column
                        label="粉丝id"
                        prop="userId"></el-table-column>
                <el-table-column
                        label="粉丝昵称"
                        prop="userName"></el-table-column>
            </el-table>
        </div>
        <div id="page_number">
            第{{pageNum}}页，共{{pageTotal}}条
        </div>
        <div id="page_content" style="float: right">
            <el-pagination
                    background
                    layout="prev, pager, next"
                    :total="pageTotal"
                    :page-size="pageSize"
                    @current-change="pageData">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import {ElMessage} from "element-plus";

    export default {
        name: "Follow",
        data(){
            return{
                follower:{
                    userId:'',
                    userName:'',
                },
                followers:[
                ],
                returnFollow:[],
                head:[],
                pageNum:1,      //当前页
                pageTotal:0,    //总条数
                pageSize:3//每页显示几条
            }
        },
        methods:{
            init(){
                this.followers.pageNumf=this.pageNum;
                this.followers.pageSizef=this.pageSize;
                this.followers.userId=sessionStorage.getItem("userId");
                //把json对象转为字符串
                var param=this.$qs.stringify(this.followers);
                console.log(param);
                this.$axios
                    .post("/user/findFollow",param)
                    .then(rst=>{
                        //console.log(rst);
                        this.pageTotal=rst.data.total;
                        this.followers=rst.data.list;
                        for(var i = 0;i < this.pageSize; i++){
                            this.head[i] = this.followers[i].headPortrait;
                        }
                        console.log(this.pageTotal);
                    }).catch(ex=>{
                    console.log(ex);
                });
            },
            pageData(curr){
                this.pageNum=curr;
                this.init();
            },
        },
        created() {
            this.init();

        }
    }
</script>

<style scoped>

    #show_content{
        text-align: center;
        background-color: #fff;
        border-radius: 20px;
        /*width: 100%;
        height: 100%;
        margin-top: 300px;*/
        position: absolute;
        margin: auto;
        top: 30px;
        left: 0;
        right: 0;
        bottom: 0;
        height: 80%;
        width:  70%;
    }
</style>