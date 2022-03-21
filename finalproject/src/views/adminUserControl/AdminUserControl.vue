<template>
    <div class="userControl">
    <div id="postSearch">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="用户Id">
                <el-input v-model="formInline.userId" placeholder="用户Id"></el-input>
            </el-form-item>
            <el-form-item label="用户名">
                <el-input v-model="formInline.userName" placeholder="用户名"></el-input>
            </el-form-item>
            <el-form-item label="当前状态">
                <el-select v-model="formInline.userStatus" placeholder="当前状态">
                    <el-option label="正常" value="1"></el-option>
                    <el-option label="封禁" value="2"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" v-on:click="findByParam()">查询</el-button>
            </el-form-item>
        </el-form>
    </div>
    <div id="userTable">
        <div id="show_table">
            <el-table :data="posts"  >
                <el-table-column
                        label="用户ID"
                        prop="userId"
                       ></el-table-column>
                <el-table-column
                        label="用户头像">
                    <template #default="scope">
                        <el-avatar :src="head[scope.$index]"></el-avatar>
                    </template>
                </el-table-column>
                <el-table-column
                        label="用户名"
                        prop="userName"></el-table-column>
                <el-table-column
                        label="帖子数量"
                        prop="postNum" ></el-table-column>
                <el-table-column
                        label="博客数量"
                        prop="blogNum"></el-table-column>
                <el-table-column
                        label="当前状态"
                        prop="userStatus"
                        :formatter="stateFormat"></el-table-column>
                <el-table-column
                        label="操作">
                        <template #default="scope">
                            <span v-if="scope.row.userStatus === 1" >
                                <el-button type="danger" v-on:click="banEvent(scope.row.userId,2)">封禁</el-button>
                            </span>
                            <span v-else >
                                <el-button type="success" v-on:click="banEvent(scope.row.userId,1)">解封</el-button>
                            </span>
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

<script scoped>
    import { ElMessage } from 'element-plus'
    export default {
        name: "AdminUserControl",
        data() {
            return {
                formInline: {
                    userId: '',
                    userName:'',
                    userStatus: ''
                },
                head:[],
                posts:[],
                posts2:[],
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
                this.$axios.post("user/GetUserInfo",param).then(rst => {
                    this.posts = rst.data.list;
                    for(var i = 0;i < this.pageSize; i++){
                        this.head[i] = this.posts[i].headPortrait;
                    }
                    this.pageTotal = rst.data.total;
                }).catch(ex=>{
                    console.log(ex);
                });
            },
            stateFormat(row) {
                if(row.userStatus == 1)
                {
                    return '正常'
                }else{
                    return '封禁'
                }
            },
            findByParam(){
                this.posts2.pageNum = this.pageNum;
                this.posts2.pageSize = this.pageSize;
                this.posts2.userId =this.formInline.userId;
                this.posts2.userName =this.formInline.userName;
                this.posts2.userStatus =this.formInline.userStatus;
                //把json对象转为字符串
                var param = this.$qs.stringify(this.posts2);
                console.log(param);
                this.$axios.post("/user/GetUserInfoByParam",param).then(rst => {
                     console.log(rst);
                    this.posts = rst.data.list;
                    for(var i = 0;i < this.pageSize; i++){
                        this.head[i] = this.posts[i].headPortrait;
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
            banEvent(userId,type){
                this.$confirm('确定封禁该用户?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    this.deletes.userId = userId;
                    this.deletes.banType =type;
                    var param = this.$qs.stringify(this.deletes);
                    console.log(param);
                    this.$axios.post("user/banUser",param).then(rst => {
                        if(rst.data == 1){
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
            },
        },
        created() {
            this.init();

        }
    }
</script>

<style scoped>

</style>