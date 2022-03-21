<template>
    <div id="container">
        <div id="postSearch">
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item label="主题帖Id">
                    <el-input v-model="formInline.articleId" placeholder="主题帖Id"></el-input>
                </el-form-item>
                <el-form-item label="标题">
                    <el-input v-model="formInline.title" placeholder="标题"></el-input>
                </el-form-item>
                <el-form-item label="发布者Id">
                    <el-input v-model="formInline.userId" placeholder="发布者Id"></el-input>
                </el-form-item>
                <el-form-item label="发布者用户名">
                    <el-input v-model="formInline.userName" placeholder="发布者用户名"></el-input>
                </el-form-item>
                <el-form-item label="标签类型">
                    <el-select v-model="formInline.lableId" placeholder="标签类型">
                        <el-option label="加精" value="1"></el-option>
                        <el-option label="置顶" value="2"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="findByParam()">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div id="postTable">
            <div id="show_table">
                <el-table :data="posts">
                    <el-table-column
                            label="发布者ID"
                            prop="userId"></el-table-column>
                    <el-table-column
                            label="用户头像">
                        <template #default="scope">
                            <el-avatar :src="head[scope.$index]"></el-avatar>
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="用户名"
                            prop="userbaseinfo.userName"></el-table-column>
                    <el-table-column
                            label="帖子ID"
                            prop="articleId"></el-table-column>
                    <el-table-column
                            label="主题"
                            prop="title"></el-table-column>
                    <el-table-column
                            label="帖子类型"
                            prop="lableId"
                            :formatter="stateFormat"></el-table-column>
                    <el-table-column
                            label="发布时间"
                            prop="date"></el-table-column>

                    <el-table-column
                            label="操作">
                        <template #default="scope">
                                <el-popconfirm
                                        confirmButtonText="置顶"
                                        cancelButtonText="加精"
                                        confirm-button-type="danger"
                                        cancel-button-type="warning"
                                        icon="el-icon-s-tools"
                                        iconColor="red"
                                        title="置顶/加精"
                                        @confirm="TopEvent(scope.row)"
                                        @cancel="EliteEvent(scope.row)"
                                >
                                    <template #reference>
                                        <el-button type="text" class="el-icon-edit">编辑</el-button>
                                    </template>
                                </el-popconfirm>
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
        name: "AdminPostControl",
        data() {
            return {
                postInfo:{
                    articleId:'',
                    userId:'',
                    title:'',
                    views:'',
                    date:'',
                    commentCount:'',
                    markedCount:'',
                    likedCount:'',
                    lableId:'',
                    userName:'',
                    headPortrait:''
                },
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
                pageTotal:0,    //总条数
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
                this.$axios.post("post/findAllPosts",param).then(rst => {
                    this.posts = rst.data.list;
                    for(var i = 0;i < this.pageSize; i++){
                        this.head[i] = this.posts[i].userbaseinfo.headPortrait;
                    }
                    this.pageTotal = rst.data.total;
                }).catch(ex=>{
                    console.log(ex);
                });
            },
            stateFormat(row) {
                if (row.lableId === "1") {
                    return '精华'
                } else if (row.lableId === "2") {
                    return '置顶'
                }else{
                    return '普通'
                }
            },
            findByParam(){
                this.posts2.pageNum = this.pageNum;
                this.posts2.pageSize = this.pageSize;
                this.posts2.articleId =this.formInline.articleId;
                this.posts2.title=this.formInline.title;
                this.posts2.userId =this.formInline.userId;
                this.posts2.userName =this.formInline.userName;
                this.posts2.lableId =this.formInline.lableId;
                //把json对象转为字符串
                var param = this.$qs.stringify(this.posts2);
                console.log(param);
                this.$axios.post("post/findByParam",param).then(rst => {
                    // console.log(rst);
                    this.posts = rst.data.list;
                    for(var i = 0;i < this.pageSize; i++){
                        this.head[i] = this.posts[i].userbaseinfo.headPortrait;
                    }
                    this.pageTotal = rst.data.total;
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
            EliteEvent(row){
                this.edits.articleId = row.articleId;
                this.edits.changeType = 1;
                var param = this.$qs.stringify(this.edits);
                this.$axios.post("post/changeLable",param).then(rst => {
                    if(rst.data == 1){
                        ElMessage.success({
                            message: '修改成功！',
                            type: 'success'
                        });
                        this.init();
                    }else{
                        ElMessage.warning({
                            message: '修改失败！',
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
            },
            TopEvent(row){
                this.edits.articleId = row.articleId;
                this.edits.changeType = 2;
                var param = this.$qs.stringify(this.edits);
                this.$axios.post("post/changeLable",param).then(rst => {
                    if(rst.data == 1){
                        ElMessage.success({
                            message: '修改成功！',
                            type: 'success'
                        });
                        this.init();
                    }else{
                        ElMessage.warning({
                            message: '修改失败！',
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
            }
        },
        created() {
            this.init();

        }
    }
</script>

<style scoped>


</style>