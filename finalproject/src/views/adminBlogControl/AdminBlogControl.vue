<template>
    <div id="container">
        <div id="BlogSearch">
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item label="博客Id">
                    <el-input v-model="formInline.articleId" placeholder="博客Id"></el-input>
                </el-form-item>
                <el-form-item label="标题">
                    <el-input v-model="formInline.title" placeholder="标题"></el-input>
                </el-form-item>
                <el-form-item label="作者Id">
                    <el-input v-model="formInline.userId" placeholder="作者Id"></el-input>
                </el-form-item>
                <el-form-item label="作者用户名">
                    <el-input v-model="formInline.userName" placeholder="作者用户名"></el-input>
                </el-form-item>
                <el-form-item label="博客类型">
                    <el-select v-model="formInline.lableId" placeholder="博客类型">
                        <el-option label="学习" value="1"></el-option>
                        <el-option label="生活" value="2"></el-option>
                        <el-option label="科技" value="3"></el-option>
                        <el-option label="游戏" value="4"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="findByParam()">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div id="BlogTable">
            <div id="show_table">
                <el-table :data="posts">
                    <el-table-column
                            label="作者ID"
                            prop="userId"></el-table-column>
                    <el-table-column
                            label="作者头像">
                        <template #default="scope">
                            <el-avatar :src="head[scope.$index]"></el-avatar>
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="用户名"
                            prop="userbaseinfo.userName"></el-table-column>
                    <el-table-column
                            label="博客ID"
                            prop="articleId"></el-table-column>
                    <el-table-column
                            label="标题"
                            prop="title"></el-table-column>
                    <el-table-column
                            label="文章标签"
                            prop="area"
                            :formatter="stateFormat"></el-table-column>
                    <el-table-column
                            label="发布时间"
                            prop="date"></el-table-column>

                    <el-table-column
                            label="操作">
                        <template #default="scope">
                            <el-button type="text"
                                       class="el-icon-delete"
                                       v-on:click="removeEvent(scope.row)">删除</el-button>
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
        name: "AdminBlogControl",
        data() {
            return {
                formInline: {
                    title:'',
                    articleId:'',
                    userId: '',
                    userName:'',
                    lableId: ''
                },
                head:[],
                posts:[],
                posts2:[],
                edits:[],
                deletes:[],
                pageNum:1,      //当前页
                pageTotal:'',    //总条数
                pageSize:5      //每页显示几条
            }
        },
        methods: {
            init(){
                this.posts.pageNum = this.pageNum;
                this.posts.pageSize = this.pageSize;
                //把json对象转为字符串
                var param = this.$qs.stringify(this.posts);
                //console.log(param);
                this.$axios.post("post/findAllBlogs",param).then(rst => {
                    this.posts = rst.data.list;
                    console.log(this.posts);
                    this.pageTotal = rst.data.total;
                    for(var i = 0;i < this.pageSize; i++){
                        this.head[i] = this.posts[i].userbaseinfo.headPortrait;
                    }

                }).catch(ex=>{
                    console.log(ex);
                });
            },
            stateFormat(row) {
                if (row.area === "1") {
                    return '学习'
                } else if (row.area === "2") {
                    return '生活'
                }else if (row.area === "3"){
                    return '科技'
                }else{
                    return '游戏'
                }
            },
            findByParam(){
                this.posts2.pageNum = this.pageNum;
                this.posts2.pageSize = this.pageSize;
                this.posts2.articleId =this.formInline.articleId;
                this.posts2.title=this.formInline.title;
                this.posts2.userId =this.formInline.userId;
                this.posts2.userName =this.formInline.userName;
                this.posts2.area =this.formInline.lableId;
                //把json对象转为字符串
                var param = this.$qs.stringify(this.posts2);
                console.log(param);
                this.$axios.post("post/findBlogsByParam",param).then(rst => {
                    // console.log(rst);
                    this.posts = rst.data.list;
                    this.pageTotal = rst.data.total;
                    for(var i = 0;i < this.pageSize; i++){
                        this.head[i] = this.posts[i].userbaseinfo.headPortrait;
                    }
                }).catch(ex=>{
                    console.log(ex);
                });
            },
            pageData(curr){
                this.pageNum = curr;
                this.init();
            },
            removeEvent(row){
                this.$confirm('确定删除该贴?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deletes.articleId = row.articleId;
                    var param = this.$qs.stringify(this.deletes);
                    this.$axios.post("post/delete",param).then(rst => {
                        if(rst.data == 1){
                            ElMessage.success({
                                message: '删除成功！',
                                type: 'success'
                            });
                            this.init();
                        }else{
                            ElMessage.warning({
                                message: '删除失败！',
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
                        message: '已取消删除'
                    });
                });
            },
        },
        created() {
            this.init();

        }
    }
</script>

<style scoped>


</style>