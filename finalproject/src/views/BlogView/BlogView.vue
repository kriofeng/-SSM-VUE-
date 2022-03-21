<template>
    <div class="back-panel" ref="back">
        <div class="article-detail">
            <div class="article-container">
                <div class="article-container__content">
                    <div class="title-container">
                        <h1 class="title" >{{articleInfo.title}}</h1>
                        <div class="article-read-panel">
                            <div class="article-read-info">
                                &nbsp;&nbsp;<span>{{this.articleInfo.date}}</span>&nbsp;&nbsp;
                                &nbsp;&nbsp;<span>{{this.articleInfo.views}}阅读</span>&nbsp;&nbsp;
                                &nbsp;&nbsp;<span>{{this.articleInfo.likedCount}}喜欢</span>&nbsp;&nbsp;
                                &nbsp;&nbsp;<span>{{this.articleInfo.commentCount}}评论</span>&nbsp;&nbsp;
                            </div>
                        </div>
                    </div>
                    <div class="article-up-info">
                        <div class="up-left">
                            <div class="avatar-container">
                            <div class="up-avatar-panel">
                                <el-avatar :src="this.writerInfo.headPortrait"></el-avatar>
                            </div>
                            </div>
                            <div class="name-panel">
                                <div class="up-name">{{writerInfo.userName}}</div>
                            </div>
                            <div class="up-button" style="position: absolute;right: 10%;top:10%" v-show="buttonShow===1" v-on:click="followTo">
                                <el-button type="danger" >关注</el-button>
                            </div>
                            <div class="up-button" style="position: absolute;right: 10%;top:10%" v-show="buttonShow===2" v-on:click="cancleFollowTo">
                                <el-button type="danger" plain>已关注</el-button>
                            </div>
                        </div>
                    </div>
                    <div class="title-line"></div>
                    <div class="article-content" id="article-content">
                        <div id="read-article-holder" class="normal-article-holder read-article-holder" >
                            <div v-html="articleInfo.content"></div>
                        </div>
                    </div>
                    <div class="article-footer-box">
                        <span class="footer-text">未经允许禁止转载</span>
                    </div>
                </div>
            </div>
            <div id="comment-wrapper" class="comment-wrapper">
                <div class="comment-m">
                    <div class="comment">
                        <div class="b-head">
                            <span class="b-head-t results">{{articleInfo.commentCount}}</span>
                            <span class="b-head-t">评论</span>
                        </div>
                        <div class="title-line2"></div>
                        <div class="article-comment" style="position: relative">
                            <div class="bb-comment">
                                <div class="comment-send">
                                    <div class="user-face">
                                        <el-avatar class="user-head"
                                                   :src="headPortrait"
                                                   style="margin-top:80px; width: 60px;height: 60px;margin-left: -5px"></el-avatar>
                                    </div>
                                    <div class="textarea-container" style="width: 620px;margin-top:-85px;margin-left: 70px">
                                            <el-input type="textarea" :cols="80" name="msg" :rows="6"
                                                      placeholder="发一条友善的评论吧" class="ipt-txt"
                                                      v-model="commentInfo.content"
                                                      style="white-space: pre-wrap;"
                                                      resize="none"></el-input>
                                    </div>
                                    <el-button class="comment-submit"
                                               aria-multiline="true"
                                               style="
                                               width:90px;
                                               margin-right: 100px;
                                               margin-top: 80px;
                                               text-align: center;"
                                               v-on:click="postComment">发表评论</el-button>
                                </div>
                                <div class="title-line3"></div>
                                <div class="comment-list">
                                    <div v-for="(i,index) in commentList" style="margin-top: 40px">
                                        <div class="user-face"
                                             style="float: left">
                                            <el-avatar :src="i.headPortrait" style="width: 50px;height: 50px"></el-avatar>
                                        </div>
                                        <div class="commentContent" style="margin-left: 80px">
                                            <div class="user">
                                                <span class="name" style="">{{i.userName}}</span>
                                            </div>
                                            <p class="text">{{i.content}}</p>
                                            <div class="info" style="margin-top: 10px;margin-bottom: -10px">
                                                <span class="time">{{i.date}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                                <span class="reply btn-hover btn-highlight" @click="fatherReply(i,index)">&nbsp;&nbsp;&nbsp;回复&nbsp;&nbsp;&nbsp;</span>
                                            </div>
                                            <div class="reply-box">
                                                <div class="reply-item reply-wrap">
                                                    <div v-for="(j) in replyMap[i.commentId]">
                                                        <el-avatar class="reply-face" :src="j.headPortrait"
                                                                   style="margin-top: 20px;
                                                                          width: 35px;
                                                                          height: 35px;"></el-avatar>
                                                        <div class="reply-con" >
                                                            <div class="user">
                                                                <span class="name"
                                                                      style="
                                                                ">{{j.userName}}&nbsp;&nbsp;回复&nbsp;&nbsp;{{j.replyName}}</span>
                                                                <span class="text-con" style="margin-right: 580px">{{j.content}}</span>
                                                            </div>
                                                        </div>
                                                        <div class="info2">
                                                            <span class="time" >{{j.date}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                                            <span class="reply btn-hover btn-highlight " @click="childReply(j,index)">&nbsp;&nbsp;&nbsp;回复&nbsp;&nbsp;&nbsp;</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div v-if="showBackComment === index">
                                            <div class="comment-send">
                                                <div class="user-face">
                                                    <el-avatar class="user-head"
                                                               :src="headPortrait"
                                                               style="margin-top:40px; width: 50px;height: 50px;margin-left: 5px"></el-avatar>
                                                </div>
                                                <div class="textarea-container" style="width: 600px;margin-top:-65px;margin-left: 70px">
                                                    <el-input type="textarea" :cols="80" name="msg" :rows="3"
                                                              :placeholder="'回复@'+this.replyTo.userName" class="ipt-txt"
                                                              v-model="commentInfo.reply"
                                                              style="white-space: pre-wrap;"
                                                              resize="none"></el-input>
                                                </div>
                                                <el-button class="comment-submit2"
                                                           aria-multiline="true"
                                                           style="
                                                            margin-top: -72px;
                                                            margin-left: 680px;
                                                            height: 70px;
                                                            width:70px;
                                                            text-align: center;"
                                                            v-on:click="postReply()">发表评论</el-button>
                                            </div>
                                            </div>
                                            <div v-else></div>
                                        </div>
                                        <div class="title-line4"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right-side-bar on is-mini-page">
                <div class="side-toolbar">
                    <el-affix position="bottom" :offset="200" style="margin-left: 920px" v-on:click="toChat">
                        <el-button  type="success" icon="el-icon-chat-dot-square" circle ></el-button>
                    </el-affix>
                    <div v-if="isMarked">
                        <el-affix position="bottom" :offset="250" style="margin-left: 920px" >
                            <el-button type="warning" icon="el-icon-star-off" circle v-on:click="cancleMark"></el-button>
                        </el-affix>
                    </div>
                    <div v-else>
                        <el-affix position="bottom" :offset="250" style="margin-left: 920px" >
                            <el-button  icon="el-icon-star-off" circle @click="markTo"></el-button>
                        </el-affix>
                    </div>

                    <div v-if="isLiked">
                    <el-affix position="bottom" :offset="300" style="margin-left: 920px">
                        <el-button  type="danger" icon="iconfont icon-dianzan" circle v-on:click="cancleLike"></el-button>
                    </el-affix>
                    </div>
                    <div v-else>
                        <el-affix position="bottom" :offset="300" style="margin-left: 920px">
                            <el-button  icon="iconfont icon-dianzan" circle v-on:click="likeTo"></el-button>
                        </el-affix>
                    </div>

                    <el-affix position="bottom" :offset="150" style="margin-left: 920px">
                        <el-button  icon="el-icon-arrow-up" circle @click="toTop"></el-button>
                    </el-affix>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import { ElMessage } from 'element-plus'
    import Tinymce from '@/components/tinymce/index'
    export default {
        name: "BlogView",
        components: {
            'editor': Tinymce
        },
        data(){
            return{
                buttonShow : 0,
                showBackComment:'',
                articleId:'',
                writerId:'',
                userId:'',
                posts:[],
                posts2:[],
                posts3:[],
                markPost:[],
                gotop: false,
                isMarked:false,
                isLiked:false,
                headPortrait:'',
                articleInfo:{
                    date:'',
                    views:'',
                    likedCount:'',
                    markedCount:'',
                    commentCount:'',
                    title:'',
                    content:'',
                },
                writerInfo:{
                    userId:'',
                    userName:'',
                    headPortrait:'',
                },
                commentInfo:{
                    content:'',
                    reply:''
                },
                replyTo:{
                    userName:'',
                    userId:'',
                    commentId:''
                },
                commentList:[],
                replyMap:{}
            }
        },
        created() {
            this.init();
            this.init2();

        },
        methods:{
            followTo(){
                var fpost=[];
                fpost.followerId = this.userId;
                fpost.followedId = this.writerInfo.userId;
                var fparam = this.$qs.stringify(fpost);
                this.$axios.post("user/addFollow",fparam).then(rst=>{
                    if(rst.data ===1){
                        this.init();
                        this.init2();
                    }
                }).catch(ex=>{
                    console.log(ex)
                });
            },
            cancleFollowTo(){
                this.$confirm('确认取消关注吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var fpost=[];
                    fpost.followerId = this.userId;
                    fpost.followedId = this.writerInfo.userId;
                    var fparam = this.$qs.stringify(fpost);
                    this.$axios.post("user/deleteFollow",fparam).then(rst=>{
                        if(rst.data > 0){
                            this.init();
                            this.init2();
                        }
                    }).catch(ex=>{
                        console.log(ex)
                    });
                }).catch(() => {
                });
            },
            isFollow(){
              if(this.userId === this.writerInfo.userId){
                  this.buttonShow = 0;
              }else{
                var postFollow = [];
                postFollow.followerId = this.userId;
                postFollow.followedId = this.writerInfo.userId;
                var paramf = this.$qs.stringify(postFollow);
                this.$axios.post("user/isFollow",paramf).then(rst=>{
                    if(rst.data === 1){
                        this.buttonShow = 2;
                    }else{
                        this.buttonShow = 1;
                    }
                }).catch(ex=>{
                    console.log(ex);
                })
              }
            },
            fatherReply(i,index){
                this.replyTo.commentId = i.commentId;
                this.replyTo.userId = i.userId;
                this.replyTo.userName = i.userName;
                this.showBackComment = index;
            },
            childReply(j,index){
                this.replyTo.commentId = j.parentId;
                this.replyTo.userId = j.userId;
                this.replyTo.userName = j.userName;
                this.showBackComment = index;
            },
            getReply(){
                //遍历所有的评论获取其中的回复
                for(var i = 0; i < this.commentList.length;i++){
                    var postx=[];
                    postx.commentId = this.commentList[i].commentId;
                    var paramx = this.$qs.stringify(postx);
                    //console.log(paramx);
                    this.$axios.post("/reply/findReply",paramx).then(rst=>{
                        console.log(rst.data);
                        if(rst.data[0] != null){
                            this.replyMap[rst.data[0].parentId] = rst.data;
                        }
                    }).catch(ex=>{
                        console.log(ex);
                    });
                }
                console.log(this.replyMap);
            },
            readComment(){
                //获取评论列表
                this.posts.articleId = this.$route.query.articleId;
                var param = this.$qs.stringify(this.posts);
                this.$axios.post("comments/findAllComments",param).then(rst=>{
                    console.log(rst.data);
                    this.commentList = rst.data;
                    this.getReply();
                }).catch(ex=>{
                    console.log(ex);
                });
            },
            init(){
                this.userId = sessionStorage.getItem("userId");
                this.articleId = this.$route.query.articleId
                this.posts.articleId = this.$route.query.articleId;
                var param = this.$qs.stringify(this.posts);
                //从数据库中查文章
                this.$axios.post("/post/findBlogById",param).then(rst=>{
                    console.log(rst.data);
                    this.articleInfo.title = rst.data.title;
                    this.articleInfo.content = rst.data.content;
                    this.articleInfo.date = rst.data.date;
                    this.articleInfo.likedCount = rst.data.likedCount;
                    this.articleInfo.commentCount = rst.data.commentCount;
                    this.articleInfo.markedCount = rst.data.markedCount;
                    this.articleInfo.views = rst.data.views;
                    this.writerInfo.userId = rst.data.userId;
                    this.writerInfo.userName = rst.data.userName;
                    this.writerInfo.headPortrait = rst.data.headPortrait;

                    this.isFollow();
                }).catch(ex=>{
                    console.log(ex);
                });



                this.posts2.userId = sessionStorage.getItem("userId");
                this.posts2.articleId = this.articleId;
                var param = this.$qs.stringify(this.posts2);
                this.$axios.post("/post/checkMarked",param).then(rst=>{
                    if(rst.data === 1){
                        this.isMarked = true;
                    }else{
                        this.isMarked = false;
                    }
                }).catch(ex=>{

                });

                this.posts3.userId = sessionStorage.getItem("userId");
                this.posts3.articleId = this.articleId;
                var param = this.$qs.stringify(this.posts3);
                this.$axios.post("/post/checkLiked",param).then(rst=>{
                    if(rst.data === 1){
                        this.isLiked = true;
                    }else{
                        this.isLiked = false;
                    }
                }).catch(ex=>{
                    console.log(ex);
                });

                this.readComment();



            },
            postReply(){
                if(this.commentInfo.reply === ''){
                    console.log(this.replyTo)
                    ElMessage.warning({
                        message: '评论内容不能为空！',
                        type: 'warning'
                    });
                }else{
                    var replyId = this.replyTo.commentId+"r";
                    var postTo = [];
                    var postT2 = [];
                    var p3 = [];
                    postTo.commentId = this.replyTo.commentId;
                    var param = this.$qs.stringify(postTo);
                    this.$axios.post("/comments/replyCount",param).then(rst=>{
                        replyId = replyId + (rst.data+1);
                        postT2.replyId = replyId;
                        postT2.parentId = this.replyTo.commentId;
                        postT2.userId = this.userId;
                        postT2.content = this.commentInfo.reply;
                        postT2.replyTo = this.replyTo.userId;
                        var _this = this;
                        let yy = new Date().getFullYear();
                        let mm = new Date().getMonth()+1;
                        let dd = new Date().getDate();
                        let hh = new Date().getHours();
                        let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
                        let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
                        _this.gettime = yy+'/'+mm+'/'+dd+' '+hh+':'+mf+':'+ss;
                        postT2.date=_this.gettime;
                        var param2 = this.$qs.stringify(postT2);
                        console.log(param2);
                        this.$axios.post("/reply/addReply",param2).then(rst=>{
                            if(rst.data === 1){
                                ElMessage.success({
                                    message: '评论已发布！',
                                    type: 'success'
                                });
                                this.init();
                                this.init2();
                                this.commentInfo.reply = "";
                            }else{
                                console.log(rst.data);
                                ElMessage.error({
                                    message: '服务器错误！',
                                    type: 'error'
                                });
                            }
                        }).catch(ex=>{
                            ElMessage.error({
                                message: '服务器错误！',
                                type: 'error'
                            });
                            console.log(ex);
                        });
                    }).catch(ex=>{
                        console.log(ex);
                        ElMessage.danger({
                            message: '服务器错误！',
                            type: 'danger'
                        });
                    });
                }
            },
            postComment(){
                if(this.commentInfo.content === ''){
                    ElMessage.warning({
                        message: '评论内容不能为空！',
                        type: 'warning'
                    });
                }else{
                    var commentId = this.articleId+"c";
                    var postTo = [];
                    var postT2 = [];
                    var p3 = [];
                    postTo.articleId = this.articleId;
                    var param = this.$qs.stringify(postTo);
                    this.$axios.post("/post/commentCount",param).then(rst=>{
                        commentId = commentId + (rst.data+1);
                        postT2.commentId = commentId;
                        postT2.articleId = this.articleId;
                        postT2.userId = this.userId;
                        postT2.content = this.commentInfo.content;
                        var _this = this;
                        let yy = new Date().getFullYear();
                        let mm = new Date().getMonth()+1;
                        let dd = new Date().getDate();
                        let hh = new Date().getHours();
                        let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
                        let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
                        _this.gettime = yy+'/'+mm+'/'+dd+' '+hh+':'+mf+':'+ss;
                        postT2.date=_this.gettime;
                        var param2 = this.$qs.stringify(postT2);
                        console.log(param2);
                        this.$axios.post("/comments/addComments",param2).then(rst=>{
                            if(rst.data === 1){
                                p3.articleId = this.articleId;
                                p3.changeType = 1;
                                var param3 = this.$qs.stringify(p3);
                                this.$axios.post("/post/changeCommentCount",param3).then(rst=>{
                                    if(rst.data > 0){
                                        ElMessage.success({
                                            message: '评论已发布！',
                                            type: 'success'
                                        });
                                        this.init();
                                        this.init2();
                                        this.commentInfo.content = "";
                                    }
                                }).catch(ex=>{
                                    console.log(ex);
                                    ElMessage.error({
                                        message: '服务器错误！',
                                        type: 'error'
                                    });
                                });
                            }else{
                                console.log(rst.data);
                                ElMessage.error({
                                    message: '服务器错误！',
                                    type: 'error'
                                });
                            }
                        }).catch(ex=>{
                            ElMessage.error({
                                message: '服务器错误！',
                                type: 'error'
                            });
                            console.log(ex);
                        });
                    }).catch(ex=>{
                        console.log(ex);
                        ElMessage.danger({
                            message: '服务器错误！',
                            type: 'danger'
                        });
                    });
                }
            },
            toChat(){
                let loc = this.$refs.back.scrollTop;
                if  (loc > 1000){
                    const timeTop = setInterval(() => {
                        this.$refs.back.scrollTop  = this.$refs.back.scrollTop  = loc -= 50;
                        if (loc <= 1000) {
                            clearInterval(timeTop);
                        }
                    }, 10);
                }else{
                    const timeTop = setInterval(() => {
                        this.$refs.back.scrollTop  = this.$refs.back.scrollTop  = loc += 50;
                        if (loc >= 1000) {
                            clearInterval(timeTop);
                        }
                    }, 10);
                }
            },
            toTop(){
                let top = this.$refs.back.scrollTop;
                //实现滚动效果
                const timeTop = setInterval(() => {
                    this.$refs.back.scrollTop  = this.$refs.back.scrollTop  = top -= 50;
                    if (top <= 0) {
                        clearInterval(timeTop);
                    }
                }, 10);
            },
            markTo(){
                this.markPost.userId = sessionStorage.getItem("userId");
                this.markPost.articleId = this.articleId;
                var param = this.$qs.stringify(this.markPost);
                this.$axios.post("/post/markTo",param).then(rst=>{
                if(rst.data === 1){
                    var change=[];
                    change.articleId = this.articleId;
                    change.changeType = 1;
                    param = this.$qs.stringify(change);

                    this.$axios.post("/post/markChange",param).then(rst=>{
                        if(rst.data > 0){
                            ElMessage.success({
                                message: '收藏成功！',
                                type: 'success'
                            });
                            this.init();
                        }else{
                            ElMessage.error({
                                message: '收藏失败！',
                                type: 'error'
                            });
                        }
                            }).catch(ex=>{
                            console.log(ex);
                            ElMessage.error({
                            message: '服务器错误！',
                            type: 'error'
                        });
                            });
                    }else{
                    ElMessage.error({
                    message: '收藏失败！',
                    type: 'error'
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
            cancleMark(){
                console.log("xx");
                this.markPost.userId = sessionStorage.getItem("userId");
                this.markPost.articleId = this.articleId;
                var param = this.$qs.stringify(this.markPost);
                this.$axios.post("/post/cancleMarked",param).then(rst=>{
                    if(rst.data === 1){
                        var change=[];
                        change.articleId = this.articleId;
                        change.changeType = -1;
                        param = this.$qs.stringify(change);

                        this.$axios.post("/post/markChange",param).then(rst=>{
                            if(rst.data > 0){
                                ElMessage.success({
                                    message: '取消收藏成功！',
                                    type: 'success'
                                });
                                this.init();
                            }else{
                                ElMessage.error({
                                    message: '操作失败！',
                                    type: 'error'
                                });
                            }
                        }).catch(ex=>{
                            console.log(ex);
                            ElMessage.error({
                                message: '服务器错误！',
                                type: 'error'
                            });
                        });
                    }else{
                        ElMessage.error({
                            message: '操作失败！',
                            type: 'error'
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
            likeTo(){
                this.markPost.userId = sessionStorage.getItem("userId");
                this.markPost.articleId = this.articleId;
                var param = this.$qs.stringify(this.markPost);
                this.$axios.post("/post/likeTo",param).then(rst=>{
                    if(rst.data === 1){
                        var change=[];
                        change.articleId = this.articleId;
                        change.changeType = 1;
                        param = this.$qs.stringify(change);

                        this.$axios.post("/post/likeChange",param).then(rst=>{
                            if(rst.data >0){
                                ElMessage.success({
                                    message: '点赞成功！',
                                    type: 'success'
                                });
                                this.init();
                            }else{
                                ElMessage.error({
                                    message: '点赞失败！',
                                    type: 'error'
                                });
                            }
                        }).catch(ex=>{
                            console.log(ex);
                            ElMessage.error({
                                message: '服务器错误！',
                                type: 'error'
                            });
                        });
                    }else{
                        ElMessage.error({
                            message: '点赞失败！',
                            type: 'error'
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
            cancleLike(){
                console.log("xx");
                this.markPost.userId = sessionStorage.getItem("userId");
                this.markPost.articleId = this.articleId;
                var param = this.$qs.stringify(this.markPost);
                this.$axios.post("/post/cancleLiked",param).then(rst=>{
                    if(rst.data === 1){
                        var change=[];
                        change.articleId = this.articleId;
                        change.changeType = -1;
                        param = this.$qs.stringify(change);

                        this.$axios.post("/post/likeChange",param).then(rst=>{
                            if(rst.data >0){
                                ElMessage.success({
                                    message: '取消点赞成功！',
                                    type: 'success'
                                });
                                this.init();
                            }else{
                                ElMessage.error({
                                    message: '操作失败！',
                                    type: 'error'
                                });
                            }
                        }).catch(ex=>{
                            console.log(ex);
                            ElMessage.error({
                                message: '服务器错误！',
                                type: 'error'
                            });
                        });
                    }else{
                        ElMessage.error({
                            message: '操作失败！',
                            type: 'error'
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
            init2(){
                this.posts.userId = sessionStorage.getItem("userId");
                var param = this.$qs.stringify(this.posts);
                this.$axios.post("user/findUser",param).then(rst=>{
                    console.log(rst);
                    if(rst.data != null){
                        this.headPortrait = rst.data.headPortrait;
                    }
                }).catch(ex=>{
                    console.log(ex);
                });
            },
            load () {
                this.count += 2
            }
        }
    }
</script>

<style scoped>
    @import "../../assets/icon/iconfont.css";
    body {
        margin: 0;
        font-family: -apple-system,BlinkMacSystemFont,Helvetica Neue,Helvetica,Arial,PingFang SC,Hiragino Sans GB,Microsoft YaHei,sans-serif;
        font-size: 12px;
        line-height: 1.5;
        color: #222;
        background-color: #fff;
    }
    div {
        display: block;
    }

    .back-panel{
        background-color: #f6f7f9;
        width: 100%;
        height: 800px;
        overflow-y: scroll;
    }

    .article-container {
        background-color: #fff;
        border-radius: 4px;
        -ms-flex-negative: 0;
        flex-shrink: 0;
        padding: 30px 40px 40px;
        margin-bottom: 12px;

    }
    .article-container .title-container .title {
        min-height: 39px;
        font-size: 28px;
        color: #222;
        margin-bottom: 16px;
        font-weight: 700;
        line-height: 1.4;
    }
     .article-up-info .up-left {
        display: -ms-flexbox;
        display: flex;
        -ms-flex-align: center;
        align-items: center;
    }

    .article-up-info {
        background: #fff;
        width: 800px;
        height: 44px;
        -ms-flex-pack: justify;
        justify-content: space-between;
        margin: 0 auto 30px;
    }

    .article-read-info {
        font-weight: 400;
        font-size: 13px;
        color: #999;
        transition: .3s;
    }

    .article-container .title-line {
        height: 1px;
        width: 100%;
        background: hsla(0,0%,59.2%,.21);
        margin-bottom: 40px;
    }

    .title-line2{
        width: 100%;
        height: 1px;
        background: hsla(0,0%,59.2%,.21);
        margin-bottom: -40px;
    }

    .title-line3{
        width: 100%;
        height: 1px;
        background: hsla(0,0%,59.2%,.21);
        margin-top:20px;
    }

    .title-line4{
        width: 100%;
        height: 1px;
        background: hsla(0,0%,59.2%,.21);
        margin-top:20px;
        margin-bottom: 20px;
    }

    .normal-article-holder {
        margin-top: 10px;
        max-width: 100%;
        min-height: 200px;
        line-height: 1.6;
        font-size: 17px;
        font-weight: 400;
        color: #212121;
        text-align: left;
        letter-spacing: 0;
        word-wrap: break-word;

        border: none;
    }

    .article-container__content {
        border-bottom: 1px solid #e5e9ef;
        margin-bottom: 30px;
    }

    * {
        margin: 0;
        padding: 0;
    }

    .article-detail {
        width: 900px;
        height: 1200px;
        display: -ms-flexbox;
        display: flex;
        -ms-flex-direction: column;
        flex-direction: column;
        margin: 0 auto 99px;
        position: relative;
    }

    .up-avatar-panel{
        position: absolute;
        top: 80%;
        left: 50%;
        -webkit-transform: translate(-50%, -50%);
        -moz-transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        -o-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
        width: 100%;
        height: 100%;
    }
    .article-up-info .avatar-container {
        -ms-flex-negative: 0;
        flex-shrink: 0;
        display: block;
        position: relative;
        width: 44px;
        height: 44px;
        margin-right: 12px;
    }

    .article-footer-box .footer-text {
        font-size: 13px;
        display: inline-block;
        color: silver;
        margin-bottom: 13px;
        margin-right: 10px;
    }
    .up-name {
        margin-top:15px;
        font-size: 20px;
        max-width: 155px;
        color: #212121;
        line-height: 23px;
        height: 20px;
        text-overflow: ellipsis;
        white-space: nowrap;
        display: inline-block;
        vertical-align: middle;
    }
    .bb-comment .comment-send .comment-submit, .comment-bilibili-fold .comment-send .comment-submit, .bb-comment .comment-send-lite .comment-submit, .comment-bilibili-fold .comment-send-lite .comment-submit {
        width: 70px;
        height: 64px;
        position: absolute;
        right: -80px;
        top: 0;
        padding: 4px 15px;
        font-size: 14px;
        color: #fff;
        border-radius: 4px;
        text-align: center;
        min-width: 60px;
        vertical-align: top;
        cursor: pointer;
        background-color: #00a1d6;
        border: 1px solid #00a1d6;
        transition: 0.1s;
        user-select: none;
        outline: none;
    }

    .comment-submit2{
        padding: 4px 7px;
        font-size: 14px;
        color: #fff;
        border-radius: 4px;
        text-align: center;
        background-color: #00a1d6;
        border: 1px solid #00a1d6;
        vertical-align: top;
        transition: 0.1s;
        cursor: pointer;
        user-select: none;
        outline: none;
    }

    .bb-comment button, .comment-bilibili-fold button {
        box-sizing: border-box;
    }

    .comment-wrapper .comment-m {
        padding: 20px 40px;
        background: #fff;

    }

    .info{
        color: #99a2aa;
        line-height: 14px;
        margin-top: 6px;
        font-size: 12px;
    }

    .info2{
        color: #99a2aa;
        line-height: 14px;
        margin-top: 15px;
        margin-left: 10px;
        font-size: 12px;
    }

    .name {
        color: #6d757a;
        font-size: 12px;
        font-weight: bold;
        line-height: 18px;
        padding-bottom: 4px;
        display: block;
        word-wrap: break-word;
        position: relative;
    }

    .reply{
        cursor:pointer;
    }

    .reply:hover{
        background-color: #f3faff;
        color: #00a1d6;
    }


</style>