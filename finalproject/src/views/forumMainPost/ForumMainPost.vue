<template>
    <div style="height: 650px">
    <el-scrollbar style="height: 100%" :setScrollTop="scolltop">
    <div class="outer">
        <div style="padding: 20px 0 0 270px">
        <span  style="font-size: 20px" >{{post.title}}</span>
            <div style="float: right"><span style="padding:0 20px 0 0 "><el-button type="primary" @click="Like()">点赞</el-button></span><span style="padding:0 20px 0 0 "><el-button type="primary" @click="Mark()">收藏</el-button></span></div>
        </div>
        <el-divider></el-divider>
        <div class="post">
        <el-card class="box-card" v-if="pageNum==1">
            <template #header>
                <div class="card-header">
                    <div>
                        <table>
                            <tr>
                                <td><el-avatar :size="50" :src="post.userbaseinfo.headPortrait"></el-avatar></td>
                                <td style="padding: 20px"><span>用户名：{{post.userbaseinfo.userName}}</span></td>
                                <td style="padding: 20px"><span>发布时间：{{post.date}}</span></td>
                            </tr>
                        </table>
                    </div>
                    <div>
                    <el-tag style="margin: 20px">楼主</el-tag>
<!--                    <el-button class="button" type="text" @click="scolldown()">回复</el-button>-->
                    </div>
                </div>
            </template>
            <div>
                <div v-html="post.content"></div>
            </div>
        </el-card>
<!--        </div>-->
<!--        <div class="post">-->
            <div v-for="(comment,i) in comments" >
            <el-card class="box-card" >
                <template #header>
                    <div class="card-header">
                        <div>
                            <table>
                                <tr>
                                    <td><el-avatar :size="50" :src="comment.userbaseinfo.headPortrait"></el-avatar></td>
                                    <td style="padding: 20px" ><span><div v-if="show">用户名:{{comment.userbaseinfo.userName}}</div></span></td>
                                    <td style="padding: 20px"><span>发布时间:{{comment.date}}</span></td>
                                </tr>
                            </table>
                        </div>
                        <div>
                            <span v-if="replystore[comment.commentId]!=undefined" style="padding: 10px">
                            {{replystore[comment.commentId].length}}条回复
                        </span>
                            <el-button class="button" type="text" @click="replyshow(i)">&nbsp;&nbsp;查看回复</el-button>
                        </div>
                    </div>
                </template>
                <div >
                    <div v-html="comment.content"></div>
                    <div style="background-color: rgba(0,143,230,0.03) ;margin: 30px" v-show="activeIndex===i">
                        <div style="padding: 10px"  v-for="(reply,m) in replystore[comment.commentId]" >
                            <el-avatar :size="30" :src="reply.userbaseinfo.headPortrait"></el-avatar><span style="padding:10px">用户名:{{reply.userbaseinfo.userName}}</span><span style="padding:10px">时间:{{reply.date}}</span>
                            <span style="float: right;padding: 20px"><el-button class="button" type="text" @click="showdia(m)">回复</el-button></span>
                            <br>
                            <div style="margin: 30px">
                                <span v-if="reply.userbaseinfo2!=null">回复 {{reply.userbaseinfo2.userName}}：&nbsp;&nbsp;</span>{{reply.content}}
<!--                                userbaseinfo2存放回复对象的名字，userbaseinfo存放自己的名字和id头像-->
                            </div>
                            <div style="height: 1px">
                            <el-divider ></el-divider>
                            </div>
                            <div v-show="replyIndex===m">
                                <el-input
                                        type="textarea"
                                        :rows="2"
                                        placeholder="请输入内容"
                                        v-model="textarea1">
                                </el-input>
                                <div style="float: right; padding: 3px 5px 0 0" >
                                <el-button type="primary" size="mini" @click="getReplyCount(comment.commentId,reply.userbaseinfo.userId)">回复</el-button>
                                </div>
                            </div>

                        </div>
                        <el-input
                                type="textarea"
                                :rows="2"
                                placeholder="请输入内容"
                                v-model="textarea2">
                        </el-input>
                        <div style="padding:5px 50px 0 0">
                        <el-button size="small" type="primary" @click="post2(comment.commentId)">回复</el-button>
                        </div>
                    </div>
                </div>
                <div style="position: relative;">
                    <div style="position:absolute;right:0px;bottom:0px;width:30px;">{{(pageNum-1)*pageSize+i+1}}楼</div>
                </div>
            </el-card>
            </div>

        </div>

        <div style="width:200px;margin:0 auto;">
            <br><br>
            <el-pagination
                    layout="prev, pager, next"
                    :current-page="pageNum"
                    :total="total"
                    :page-size="pageSize"
                    @current-change="pageData">
            </el-pagination>

        </div>

    </div>
        <br>
        <br>

        <div class="app-container" style="width: 1000px;margin:0 auto;">
            <editor :curValue="content" @input="newContent" />
        </div>
        <div style="padding: 0 0 0 270px" >
            <br>

            <el-button type="primary" @click="submit()">回复</el-button>
        </div>
    </el-scrollbar>

    </div>
</template>

<script scoped>
    import Tinymce from '../../components/tinymce/indexForumPost'
    export default {
        name: "ForumMainPost",
        components: {
            'editor': Tinymce
        },

        data(){
            return{
                replyIndex:-1,//回复框show
                articleId:'',
                content:'',
                postInfo:{
                    articleId:''
                },
                post:{userbaseinfo:{}},
                content:'',
                userId:'',//当前登录用户
                commentId:'',
                ccc:{},
                changeComment:{},
                date:'',
                pageSize:3,
                pageNum:1,
                total:1,
                findComment:{},
                comments:[],
                show:false,
                replys:[],
                findReplys:{},
                pageSize2:3,
                pageNum2:1,
                total2:1,
                replystore:{},
                activeIndex:-1,
                scolltop:0,
                textarea1:'',
                textarea2:'',
                po1:{},
                po2:{},
                findRC:{},
                RC:'',
                Rid:'',
                LikeCount:'',
                MarkCount:'',
                LC:{},
                clc:{},
                lt:{},
                MC:{},
                cmc:{},
                mt:{},


            }
        },

        methods:{
            Mark(){
                this.MC.userId=this.userId;
                this.MC.articleId=this.articleId;
                var param7=this.$qs.stringify(this.MC);
                this.mt.userId=this.userId;
                this.mt.articleId=this.articleId;
                var param9=this.$qs.stringify(this.mt);
                this.$axios
                    .post("post/checkMarked",param7)
                    .then(rst=>{
                        console.log(rst.data);
                        if(rst.data==0){
                            this.cmc.userId=this.userId;
                            this.cmc.changeType=1;
                            var param8=this.$qs.stringify(this.cmc);
                            this.$axios
                                .post("post/markChange",param8)
                                .then(rst=>{

                                })
                                .catch(ex=>{
                                    console.log(ex);
                                })
                            this.$axios
                                .post("post/markTo",param9)
                                .then(rst=>{
                                    this.open5();
                                })
                                .catch(ex=>{
                                    console.log(ex);
                                })
                        }
                        else{
                            this.cmc.userId=this.userId;
                            this.cmc.changeType=-1;
                            var param8=this.$qs.stringify(this.cmc);
                            this.$axios
                                .post("post/markChange",param8)
                                .then(rst=>{

                                })
                                .catch(ex=>{
                                    console.log(ex);
                                })
                            this.$axios
                                .post("post/cancleMarked",param9)
                                .then(rst=>{
                                    this.open6();
                                })
                                .catch(ex=>{
                                    console.log(ex);
                                })
                        }
                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
            },
            Like(){
                this.LC.userId=this.userId;
                this.LC.articleId=this.articleId;
                var param7=this.$qs.stringify(this.LC);
                this.lt.userId=this.userId;
                this.lt.articleId=this.articleId;
                var param9=this.$qs.stringify(this.lt);
                this.$axios
                    .post("post/checkLiked",param7)
                    .then(rst=>{
                        console.log(rst.data);
                        if(rst.data==0){
                            this.clc.userId=this.userId;
                            this.clc.changeType=1;
                            var param8=this.$qs.stringify(this.clc);
                            this.$axios
                                .post("post/likeChange",param8)
                                .then(rst=>{

                                })
                                .catch(ex=>{
                                    console.log(ex);
                                })
                            this.$axios
                                .post("post/likeTo",param9)
                                .then(rst=>{
                                    this.open3();
                                })
                                .catch(ex=>{
                                    console.log(ex);
                                })
                        }
                        else{
                            this.clc.userId=this.userId;
                            this.clc.changeType=-1;
                            var param8=this.$qs.stringify(this.clc);
                            this.$axios
                                .post("post/likeChange",param8)
                                .then(rst=>{

                                })
                                .catch(ex=>{
                                    console.log(ex);
                                })
                            this.$axios
                                .post("post/cancleLiked",param9)
                                .then(rst=>{
                                    this.open4();
                                })
                                .catch(ex=>{
                                    console.log(ex);
                                })
                        }
                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
            },
            getReplyCount(commentId,ReplyId){
                this.findRC.commentId=commentId;
                var param5=this.$qs.stringify(this.findRC);

                this.$axios
                    .post("comments/replyCount",param5)
                    .then(rst=>{
                        this.RC=rst.data;
                        this.Rid=commentId+"r"+String(this.RC);
                        this.po1.replyId=this.Rid;
                        this.po1.content=this.textarea1;
                        this.po1.parentId=commentId;
                        this.po1.replyTo=ReplyId;
                        this.po1.userId=this.userId;
                        this.getCurrentTime();
                        this.po1.date=this.date;

                        var param6=this.$qs.stringify(this.po1);
                        this.$axios
                            .post("reply/addReply",param6)
                            .then(rst=>{
                                this.open1();
                                console.log(rst);
                                this.textarea1='';
                                this.init();
                                location.reload();

                            })
                            .catch(ex=>{
                                console.log(ex);
                            })

                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
            },
            post2(commentId){
                this.findRC.commentId=commentId;
                var param6=this.$qs.stringify(this.findRC);

                this.$axios
                    .post("comments/replyCount",param6)
                    .then(rst=>{
                        this.RC=rst.data;
                        this.Rid=commentId+"r"+String(this.RC);
                        this.po1.replyId=this.Rid;
                        this.po1.content=this.textarea2;
                        this.po1.parentId=commentId;
                        this.po1.replyTo='';
                        this.po1.userId=this.userId;
                        this.getCurrentTime();
                        this.po1.date=this.date;

                        var param6=this.$qs.stringify(this.po1);
                        this.$axios
                            .post("reply/addReply",param6)
                            .then(rst=>{
                                this.open1();
                                console.log(rst);
                                this.textarea2='';
                                this.init();
                                location.reload();

                            })
                            .catch(ex=>{
                                console.log(ex);
                            })

                    })
                    .catch(ex=>{
                        console.log(ex);
                    })

            },
            scolldown(){
                this.scolltop('1000px');
            },
            showdia(m){//显示回复框
                this.replyIndex=m;
            },
            replyshow(i){
                this.activeIndex=i;
            },
            findReply(){

                console.log(this.comments.length);
                for(var i=0;i<this.comments.length;i++){
                    this.findReplys.parentId=this.comments[i].commentId;
                    var param4=this.$qs.stringify(this.findReplys);
                    console.log("2222222222222222");
                    var ci=this.comments[i].commentId;
                    console.log(ci);

                    this.$axios
                        .post("forumReply/findReply",param4)
                        .then(rst=>{

                            this.replys.push(rst.data);
                            if(rst.data!=null){
                                var key=rst.data[0].parentId;
                                var value=rst.data;
                                console.log("111111111111111111111111111");
                                console.log(key);
                                console.log(value);
                                this.replystore[key]=value;
                            }


                        })
                        .catch(ex=>{
                            console.log(ex);
                        })
                }
                console.log("33333333333333333");
                console.log(this.replystore);
            },
            pageData(curr){
                this.pageNum=curr;
                this.init();
            },
            findComments(){
                this.findComment.pageSize=this.pageSize;
                this.findComment.pageNum=this.pageNum;
                this.findComment.articleId=this.articleId;
                var param3=this.$qs.stringify(this.findComment);
                this.$axios
                    .post("forumComment/findComment",param3)
                    .then(rst=>{
                        this.comments=rst.data.list;

                        console.log(rst.data.list);
                        this.total=rst.data.total;
                        this.show=true;
                        this.findReply();

                    })
                    .catch(ex=>{
                        console.log(ex);
                    })

            },
            open1() {
                this.$notify({
                    title: '成功',
                    message: '评论成功',
                    type: 'success'
                });
            },
            open2() {
                this.$notify.error({
                    title: '错误',
                    message: '这是一条错误的提示消息'
                });
            },
            open3() {
                this.$notify({
                    title: '成功',
                    message: '点赞成功',
                    type: 'success'
                });
            },
            open4() {
                this.$notify({
                    title: '成功',
                    message: '取消点赞',
                    type: 'success'
                });
            },
            open5() {
                this.$notify({
                    title: '成功',
                    message: '收藏成功',
                    type: 'success'
                });
            },
            open6() {
                this.$notify({
                    title: '成功',
                    message: '取消收藏',
                    type: 'success'
                });
            },

            getCurrentTime() {
                //获取当前时间并打印
                var _this = this;
                let yy = new Date().getFullYear();
                let mm = new Date().getMonth()+1;
                let dd = new Date().getDate();
                let hh = new Date().getHours();
                let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
                let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
                _this.gettime = yy+'/'+mm+'/'+dd+' '+hh+':'+mf+':'+ss;
                this.date=_this.gettime;
                console.log(this.date);
                console.log(this.content);
                console.log(this.title)
            },
            init(){
                this.userId = sessionStorage.getItem("userId");
                this.findPost();
                this.findComments();
            },
            changeCommentCount(){
                this.ccc.articleId=this.articleId;
                this.ccc.changeType=1;
                var param=this.$qs.stringify(this.ccc);
                this.$axios
                    .post("post/changeCommentCount",param)
                    .then(rst=>{
                        console.log(rst);
                    }).catch(ex=>{
                    this.open2();
                    console.log(ex);
                });
            },

            submit(){
                this.changeCommentCount();
                this.commentId=this.articleId+'c'+String(this.post.commentCount+1);
                this.getCurrentTime();
                this.changeComment.commentId=this.commentId;
                this.changeComment.articleId=this.articleId;
                this.changeComment.content=this.content;
                this.changeComment.userId=this.userId;
                this.changeComment.date=this.date;
                var param2=this.$qs.stringify(this.changeComment);
                this.content='';
                this.$axios
                    .post("forumComment/commentUpdate",param2)
                    .then(rst=>{
                        if(rst.data==1){
                            this.open1();
                        }
                    })
                    .catch(ex=>{
                        console.log(ex);
                        this.open2();
                    })
                this.init();
            },
            newContent(val) {
                this.content=val;

            },
            findPost(){
                this.articleId = this.$route.query.articleId;
                this.postInfo.articleId=this.articleId;
                var param=this.$qs.stringify(this.postInfo);
                console.log(param);
                this.$axios
                    .post("FindPost/postbyid",param)
                    .then(rst=>{
                        this.post=rst.data;
                        console.log(this.post);

                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
            },
        },
        created() {
            this.init();

        }
    }
</script>

<style scoped>


    * {
        margin: 0;
        padding: 0;
    }
    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .box-card {
        width: 1000px;
    }
    body{text-align:center}
    .box-card{
        margin:0 auto;

    }
    #headers{
        width: 100%;
        text-align: center;
        line-height: 50px;
    }
    .outer{
        overflow-y:auto;
    }
    el-card{
        overflow-y:auto;
    }
</style>