<template>
    <div id="show_content">
        <div id="show_table">
            <el-table
                    :data="focuses">
                <el-table-column
                        label="头像">
                    <template #default="scope">
                        <el-avatar :src="head[scope.$index]"></el-avatar>
                    </template>
                </el-table-column>
                <el-table-column
                        label="博主id"
                        prop="userId"></el-table-column>
                <el-table-column
                        label="博主昵称"
                        prop="userName"></el-table-column>
                <el-table-column
                        label="操作">
                    <template #default="scope">
                        <el-button type="primary"
                                   class="el-icon-edit" v-on:click="concleEvent(scope.row.userId)">取关</el-button>
                    </template>
                </el-table-column>
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
        name: "Focus",
        data(){
            return{
                focus:{
                    userId:'',
                    userName:'',
                },
                focuses:[],
                concleFollow:[],
                head:[],
                pageNum:1,//当前页
                pageTotal:0,//总条数
                pageSize:4//每页显示几条
            }
        },
        methods:{
            init(){
                this.focuses.pageNum=this.pageNum;
                this.focuses.pageSize=this.pageSize;
                this.focuses.userId=sessionStorage.getItem("userId");
                //把json对象转为字符串
                var param=this.$qs.stringify(this.focuses);
                console.log(param);
                this.$axios
                    .post("/user/findFocus",param)
                    .then(rst=>{
                        //console.log(rst);
                        this.pageTotal=rst.data.total;
                        this.focuses=rst.data.list;
                        for(var i = 0;i < this.pageSize; i++){
                            this.head[i] = this.focuses[i].headPortrait;
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
            concleEvent(userId){
                this.$confirm('确定取关该用户?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'info',
                }).then(() => {
                    this.concleFollow.userId =sessionStorage.getItem("userId");
                    this.concleFollow.followedId =userId;
                    var param = this.$qs.stringify(this.concleFollow);
                    console.log(param);
                    this.$axios.post("user/concleFollow",param).then(rst => {
                        if(rst.data >= 1){
                            ElMessage.success({
                                message: '操作成功！',
                                type: 'success'
                            });
                            this.init();
                        }else{
                            ElMessage.warning({
                                message: '操作失败！',
                                type: 'warning'
                            });
                        }
                    }).catch(ex=>{
                        console.log(ex);
                        ElMessage.error({
                            message: '服务器错误！',
                            type: 'error'
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消操作'
                    });
                });
            }

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