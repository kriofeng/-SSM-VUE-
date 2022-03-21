<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20" style="height:252px;">
                    <div class="user-info">
                        <img src="../assets/img/img.jpg" class="user-avator" alt />
                        <div class="user-info-cont">
                            <div class="user-info-name">{{ name }}</div>
                            <div>{{ role }}</div>
                        </div>
                    </div>
                    <div class="user-info-list">
                        上次登录时间：
                       <div style="float: right">{{ lastTime }}</div>
                    </div>
                    <div class="user-info-list">
                        上次登录Ip：
                        <div style="float: right">{{ lastIp }}</div>
                    </div>
                </el-card>
                <el-card shadow="hover" style="height:252px;">
                    <template #header>
                        <div class="clearfix">
                            <span>文章种类情况</span>
                        </div>
                    </template>
                    学习
                    <el-progress :percentage="learnCount" color="#42b983"></el-progress>生活
                    <el-progress :percentage="lifeCount" color="#f1e05a"></el-progress>游戏
                    <el-progress :percentage="techCount"></el-progress>技术
                    <el-progress :percentage="gameCount" color="#f56c6c"></el-progress>
                </el-card>
            </el-col>
            <el-col :span="16">
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{ padding: '0px' }">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-user-solid grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num" >{{count}}</div>
                                    <div>用户数量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{ padding: '0px' }">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-tickets grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{blogCount}}</div>
                                    <div>博客数量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{ padding: '0px' }">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-chat-line-round grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{postsCount}}</div>
                                    <div>帖子数量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
                <el-card shadow="hover" style="height:403px;">
                    <template #header>
                        <div class="clearfix">
                            <span>公告发布栏</span>
                            <el-button style="float: right; padding: 3px 0" type="text" v-on:click="addNotice">发布</el-button>
                        </div>
                    </template>
                    <input type="textarea" style="width: 600px;height: 25px;margin-left: 80px" placeholder="公告标题" v-model="noticeTitle">
                    <input type="textarea" style="width: 700px;height:200px;margin-top: 20px;margin-left: 25px" v-model="noticeContent"/>
                    <div style="margin-top: 20px;margin-left: 300px"><span>当前共有{{this.noticeCount}}条公告</span></div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import Schart from "vue-schart";
    import { reactive } from "vue";
    import { ElMessage } from 'element-plus'
    export default {

        name: "dashboard",
        components: { Schart },
        data(){
            return{
                count:'0',
                blogCount:'0',
                postsCount:'0',
                learnCount:'',
                lifeCount:'',
                techCount:'',
                gameCount:'',
                sum:'',
                noticeTitle:'',
                noticeContent:'',
                noticeCount:'',
                date:''
            }
        },
        setup() {
            const name = sessionStorage.getItem("token");
            const userlastInfo = JSON.parse(localStorage.getItem(name));
            const lastTime = userlastInfo['lastTime'];
            const lastIp = userlastInfo['Ip'];
            const role = "admin";

            return {
                userlastInfo,
                lastIp,
                lastTime,
                name,
                role,
            };
        },
        methods:{
            addNotice(){
              if(this.noticeTitle === '' || this.noticeContent === ''){
                  ElMessage.warning({
                      message: '标题或内容不能为空！',
                      type: 'warning'
                  });
              }else{
                  var posts = [];
                  posts.noticeTitle = this.noticeTitle;
                  posts.noticeContent = this.noticeContent;
                  posts.noticeId = "n"+(this.noticeCount+1);
                  this.getTime();
                  posts.date = this.date;
                  var param = this.$qs.stringify(posts);
                  this.$axios.post("notice/addNotice",param).then(rst=>{
                      if(rst.data === 1){
                          ElMessage.success({
                              message: '发布成功！',
                              type: 'success'
                          });
                          this.noticeTitle = '';
                          this.noticeContent = '';
                          this.init();
                      }else{
                          ElMessage.error({
                              message: '服务器错误！',
                              type: 'error'
                          });
                      }
                  }).catch(ex=>{
                      console.log(ex);
                  })
              }
            },
            getTime(){
                var _this = this;
                let yy = new Date().getFullYear();
                let mm = new Date().getMonth()+1;
                let dd = new Date().getDate();
                let hh = new Date().getHours();
                let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
                let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
                _this.gettime = yy+'/'+mm+'/'+dd+' '+hh+':'+mf+':'+ss;
                this.date = _this.gettime
            },
            init(){
                this.$axios.post("user/Count").then(rst => {
                    this.count = rst.data;
                }).catch(ex=>{
                    console.log(ex);
                });
                this.$axios.post("post/PostCount").then(rst => {
                    this.postsCount = rst.data;
                }).catch(ex=>{
                    console.log(ex);
                });
                this.$axios.post("post/BlogCount").then(rst => {
                    this.blogCount = rst.data;
                }).catch(ex=>{
                    console.log(ex);
                });
                this.$axios.post("chart/typecnt").then(rst => {
                    console.log(rst.data);
                    this.sum = rst.data[0].cnt+rst.data[1].cnt+rst.data[2].cnt+rst.data[3].cnt
                    this.learnCount = (rst.data[0].cnt / this.sum*100).toFixed(2);
                    this.lifeCount = (rst.data[1].cnt / this.sum*100).toFixed(2);
                    this.techCount = (rst.data[2].cnt / this.sum*100).toFixed(2);
                    this.gameCount = (rst.data[3].cnt / this.sum*100).toFixed(2);
                }).catch(ex=>{
                    console.log(ex);
                });

                this.$axios.post("notice/noticeCount").then(rst=>{
                    this.noticeCount = rst.data;
                }).catch(ex=>{
                    console.log(ex);
                })

            }
        },
        created() {
            this.init();
        }
    };
</script>

<style scoped>
    .el-row {
        margin-bottom: 20px;
    }

    .grid-content {
        display: flex;
        align-items: center;
        height: 100px;
    }

    .grid-cont-right {
        flex: 1;
        text-align: center;
        font-size: 14px;
        color: #999;
    }

    .grid-num {
        font-size: 30px;
        font-weight: bold;
    }

    .grid-con-icon {
        font-size: 50px;
        width: 100px;
        height: 100px;
        text-align: center;
        line-height: 100px;
        color: #fff;
    }

    .grid-con-1 .grid-con-icon {
        background: rgb(45, 140, 240);
    }

    .grid-con-1 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-2 .grid-con-icon {
        background: rgb(100, 213, 114);
    }

    .grid-con-2 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-3 .grid-con-icon {
        background: rgb(242, 94, 67);
    }

    .grid-con-3 .grid-num {
        color: rgb(242, 94, 67);
    }

    .user-info {
        display: flex;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 2px solid #ccc;
        margin-bottom: 20px;
    }

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .user-info-cont {
        padding-left: 50px;
        flex: 1;
        font-size: 14px;
        color: #999;
    }

    .user-info-cont div:first-child {
        font-size: 30px;
        color: #222;
    }

    .user-info-list {
        font-size: 14px;
        color: #999;
        line-height: 25px;
    }

    .user-info-list span {
        margin-left: 70px;
    }

    .mgb20 {
        margin-bottom: 20px;
    }

    .todo-item {
        font-size: 14px;
    }

    .todo-item-del {
        text-decoration: line-through;
        color: #999;
    }

    .schart {
        width: 100%;
        height: 300px;
    }
</style>
