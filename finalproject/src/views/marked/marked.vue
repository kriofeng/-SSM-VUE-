<template>
        <div id="show_content">
            <div id="postTable">
                <div id="show_table">
                    <el-form>
                    <el-table :data="arins" row-style="cursor:pointer" @row-click="detailPage">
                        <el-table-column
                                label="作者"
                                prop="userName"></el-table-column>
                        <el-table-column
                                style="text-overflow: ellipsis;
                                 overflow: hidden;
                                 white-space: nowrap;
                                  "

                                :show-overflow-tooltip='true'
                                label="标题"
                                prop="title"></el-table-column>
                        <el-table-column
                                label="类型"
                                prop="type"
                                :formatter="state">
                        </el-table-column>
                        <el-table-column
                                label="操作">
                            <template #default="scope">
                                <el-button type="text"
                                           class="el-icon-delete" v-on:click="cancelMarked(scope.row)">取消收藏</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    </el-form>
                </div>
                <div id="page_number">
                    第{{pageNum}}页，共{{pageTotal}}条
                </div>
                <div id="page_content" style="float: right">
                    <el-pagination
                            background
                            layout="prev,pager,next"
                            :total="pageTotal"
                            :page-size="pageSize"
                            @current-change="pageData">
                    </el-pagination>
                </div>
            </div>
        </div>
</template>

<script>
    import { ElMessage } from 'element-plus'
    export default {
        name: "marked",
        data() {
            return {
                markedArticle:{
                    articleId:'',
                     userId:"",
                     type:"",
                     title:"",
                     content:"",
                     views:"",
                     date:"",
                     commentCount:"",
                     markedCount:"",
                     likedCount:"",
                     lableId:"",
                     articleStatus:"",
                     area:"",
                     userbaseinfo:"",
                     marked:""
                },
                userId:'',
                arins:[],
                pots:[],
                deletes:[],
                pageNum:1,//当前页
                pageTotal:0,//总条数
                pageSize:5,//每页显示几条
            }
        },
        created() {
            this.init();
        },
        methods:{
            detailPage(row) {
                //浏览量+1
                var postView = [];
                postView.articleId = row.articleId;
                var viewParam = this.$qs.stringify(postView);
                this.$axios.post("post/changeViewCount",viewParam).then(rst=>{
                    console.log(rst.data);
                }).catch(ex=>{
                    console.log(ex);
                });
                console.log(row.articleId)
                if(row.articleId[0] === 'b'){
                    this.$router.push({path:'/blogview',query:{articleId:row.articleId}});
                }else{
                    this.$router.push({path:'/forumMainPost',query:{articleId:row.articleId}});
                }
            },
            cancelMarked(row){
                this.$confirm('确定取消收藏?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deletes.articleId = row.articleId;
                    this.deletes.userId = this.userId;
                    var param = this.$qs.stringify(this.deletes);
                    this.$axios.post("post/cancleMarked",param).then(rst => {
                        if(rst.data == 1){
                            ElMessage.success({
                                message: '取消收藏成功！',
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
            },
            state(row){
                if(row.type === 2){
                    return '博客'
                }else{
                    return '帖子'
                }
            },
            init(){
                this.userId = window.sessionStorage.getItem("userId");
                this.pots.userId = window.sessionStorage.getItem("userId");
                this.pots.pageNum = this.pageNum;
                this.pots.pageSize = this.pageSize;
                var param = this.$qs.stringify(this.pots);
                //使用axios库获取后台的数据
                this.$axios.post("post/findtitle", param).then(rst=>{
                        console.log(rst);
                        this.pageTotal=rst.data.total;
                        this.arins=rst.data.list;
                        console.log(this.arins);
                    })
                    .catch(ex=>{
                        console.log(ex);
                    });
            }
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
        left: 100px;
        right: 0;
        bottom: 0;
        height: 80%;
        width:  80%;
    }

</style>