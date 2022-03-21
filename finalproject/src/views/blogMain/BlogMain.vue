<template>
    <div id="container">
        <el-row id="Menu">
            <el-col :span="4">

            </el-col>
            <el-col :span="16" >

                    <!--<div id="title" style="padding: 20px; font-size: 30px">
                        精选博客
                    </div>-->
                    <el-tabs type="border-card"  v-model="activeName">
                        <el-tab-pane label="精选博客" name="first" :key="'first'">
                            <div id="show_table">
                                <div v-for="(post,i) in posts">
                                    <el-row>
                                        <div class="title-line"></div>
                                            <el-card shadow="hover"
                                                    style="width: 90%;
                                                        height: 150px;
                                                        margin-left: 40px;
                                                        cursor: pointer;
                                                       "
                                                        v-on:click="detailPage(post.articleId)">
                                                <el-row style="height: 180px; margin-top: -20px;margin-left: -10px">
                                                <div class="left-block" style="width: 700px;margin-left: -10px;background-color: #fafafa">
                                                    <div style="margin-top: 15px;
                                                                margin-left: 20px;
                                                                font-size: 20px;
                                                                color: #212121;
                                                                overflow: hidden;
                                                                width: 500px;
                                                                text-overflow: ellipsis;
                                                                white-space:nowrap;">{{post.title}}
                                                    </div>
                                                    <div style="
                                                                margin-top: 15px;
                                                                margin-bottom: -10px;
                                                                margin-left: 20px;
                                                                ">
                                                        <div v-html="post.content"
                                                              style="white-space:nowrap;
                                                                        color: #99a2aa;
                                                                       overflow: hidden;
                                                                       text-overflow: ellipsis;
                                                                       height: 20px;"></div>
                                                    </div>
                                                    <div style=";
                                                                margin-top: 20px;
                                                                margin-left: 5px">
                                                        <div style="margin-bottom: 5px"><el-avatar :src="post.userbaseinfo.headPortrait" style="width: 32px;height: 32px"></el-avatar></div>
                                                        <div style="margin-top: -30px;
                                                                    margin-left: 60px;

                                                                    font-size: 16px;
                                                                    color: #99a2aa;
                                                                    line-height: 16px;

                                                                    max-width: 120px;
                                                                    text-overflow: ellipsis;
                                                                    overflow: hidden;
                                                                    white-space: nowrap;">{{post.userbaseinfo.userName}}</div>
                                                    </div>
                                                    <div style=" margin-top: 15px;  color: #99a2aa;">
                                                        <span style="font-size: 14px;">&nbsp;&nbsp;{{post.date}}&nbsp;&nbsp;</span>
                                                        <span style="font-size: 14px;">&nbsp;&nbsp;<i class="el-icon-star-on"></i>{{post.markedCount}}&nbsp;</span>
                                                        <span style="font-size: 14px;">&nbsp;<i class="el-icon-chat-dot-square"></i>{{post.commentCount}}&nbsp;</span>
                                                        <span style="font-size: 14px;">&nbsp;<i class="iconfont icon-dianzan"></i>{{post.likedCount}}&nbsp;</span>
                                                        <span style="font-size: 14px;">&nbsp;<i class="el-icon-view"></i>{{post.views}}&nbsp;</span>
                                                    </div>
                                                </div>
                                                <div class="right-image" style="width:160px;margin-left:2px;" v-show="post.area==='1'">
                                                    <img style="width: 150px;margin-left: 20px" src="../../assets/img/learn.png"/>
                                                </div>
                                                <div class="right-image" style="width:160px;margin-left:2px;" v-show="post.area==='2'">
                                                    <img style="width: 150px;margin-left: 20px" src="../../assets/img/life.png"/>
                                                </div>
                                                <div class="right-image" style="width:160px;margin-left:2px;" v-show="post.area==='3'">
                                                    <img style="width: 150px;margin-left: 20px" src="../../assets/img/tech.png"/>
                                                </div>
                                                <div class="right-image" style="width:160px;margin-left:2px;" v-show="post.area==='4'">
                                                    <img style="width: 150px;margin-left: 20px" src="../../assets/img/game.png"/>
                                                </div>
                                                </el-row>
                                            </el-card>
                                        <div class="title-line"></div>
                                    </el-row>
                                </div>
                            </div>
                            <div id="page_content" >
                                <el-pagination background
                                               layout="prev,pager,next" :current-page="pageNum"
                                               :total="page"
                                               :page-size="pageSize"
                                               @current-change="pageData"></el-pagination>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="我的博客" name="second" :key="'second'">
                            <div id="show_table2">
                                <div v-for="(blog,i) in myblogs">
                                    <el-row>
                                        <div class="title-line"></div>
                                        <el-card shadow="hover"
                                                 style="width: 90%;
                                                        height: 150px;
                                                        margin-left: 40px;
                                                        cursor: pointer;
                                                       "
                                                 v-on:click="detailPage(blog.articleId)">
                                            <el-row style="height: 180px; margin-top: -20px;margin-left: -10px">
                                                <div class="left-block" style="width: 700px;margin-left: -10px;background-color: #fafafa">
                                                    <div style="margin-top: 15px;
                                                                margin-left: 20px;
                                                                font-size: 20px;
                                                                color: #212121;
                                                                overflow: hidden;
                                                                width: 500px;
                                                                text-overflow: ellipsis;
                                                                white-space:nowrap;">{{blog.title}}
                                                    </div>
                                                    <div style="
                                                                margin-top: 15px;
                                                                margin-bottom: -10px;
                                                                margin-left: 20px;
                                                                ">
                                                        <div v-html="blog.content"
                                                             style="white-space:nowrap;
                                                                        color: #99a2aa;
                                                                       overflow: hidden;
                                                                       text-overflow: ellipsis;
                                                                       height: 20px;
                                                                       "></div>
                                                    </div>
                                                    <div style=";
                                                                margin-top: 20px;
                                                                margin-left: 5px">
                                                        <div style="margin-bottom: 5px"><el-avatar :src="blog.userbaseinfo.headPortrait" style="width: 32px;height: 32px"></el-avatar></div>
                                                        <div style="margin-top: -30px;
                                                                    margin-left: 60px;

                                                                    font-size: 16px;
                                                                    color: #99a2aa;
                                                                    line-height: 16px;

                                                                    max-width: 120px;
                                                                    text-overflow: ellipsis;
                                                                    overflow: hidden;
                                                                    white-space: nowrap;">{{blog.userbaseinfo.userName}}</div>
                                                    </div>
                                                    <div style=" margin-top: 15px;  color: #99a2aa;">
                                                        <span style="font-size: 14px;">&nbsp;&nbsp;{{blog.date}}&nbsp;&nbsp;</span>
                                                        <span style="font-size: 14px;">&nbsp;&nbsp;<i class="el-icon-star-on"></i>{{blog.markedCount}}&nbsp;</span>
                                                        <span style="font-size: 14px;">&nbsp;<i class="el-icon-chat-dot-square"></i>{{blog.commentCount}}&nbsp;</span>
                                                        <span style="font-size: 14px;">&nbsp;<i class="iconfont icon-dianzan"></i>{{blog.likedCount}}&nbsp;</span>
                                                        <span style="font-size: 14px;">&nbsp;<i class="el-icon-view"></i>{{blog.views}}&nbsp;</span>
                                                    </div>
                                                </div>
                                                <div class="right-image" style="width:160px;margin-left:2px;" v-show="blog.area==='1'">
                                                    <img style="width: 150px;margin-left: 20px" src="../../assets/img/learn.png"/>
                                                </div>
                                                <div class="right-image" style="width:160px;margin-left:2px;" v-show="blog.area==='2'">
                                                    <img style="width: 150px;margin-left: 20px" src="../../assets/img/life.png"/>
                                                </div>
                                                <div class="right-image" style="width:160px;margin-left:2px;" v-show="blog.area==='3'">
                                                    <img style="width: 150px;margin-left: 20px" src="../../assets/img/tech.png"/>
                                                </div>
                                                <div class="right-image" style="width:160px;margin-left:2px;" v-show="blog.area==='4'">
                                                    <img style="width: 150px;margin-left: 20px" src="../../assets/img/game.png"/>
                                                </div>
                                            </el-row>
                                        </el-card>
                                        <div class="title-line"></div>
                                    </el-row>
                                </div>
                            </div>
                            <div id="page_content2" >
                                <el-pagination background
                                               layout="prev,pager,next" :current-page="pageNum2"
                                               :total="page2"
                                               :page-size="pageSize2"
                                               @current-change="pageData2"></el-pagination>
                            </div>
                        </el-tab-pane>
                    </el-tabs>

            </el-col>
            <el-col :span="4">
                <div style="padding: 10px">
                    <el-card class="box-card2">
                        <div slot="header" class="clearfix">
                            <span  class="left" style="text-align: center;display:block;margin: 10px;"><i class="el-icon-coffee"></i>热门博客</span>
                            <div>
                                <div style="float: left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;width: 80px">
                                    <span v-on:click="detailPage(hotPost[0].articleId)" style="font-size: 16px;color: #99a2aa; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;cursor:pointer;">{{hotPost[0].title}}</span>
                                    <br><br>
                                    <span v-on:click="detailPage(hotPost[1].articleId)" style="font-size: 16px;color: #99a2aa; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;cursor:pointer;">{{hotPost[1].title}}</span>
                                </div>
                                <div style="float: right">
                                    <i class="el-icon-view"></i><span style="font-size: 16px;color: #99a2aa;">{{hotPost[0].views}}</span>
                                    <br><br>
                                    <i class="el-icon-view"></i><span style="font-size: 16px;color: #99a2aa;">{{hotPost[1].views}}</span>
                                </div>
                            </div>
                        </div>
                        <br><br><br>
                        <div class="ItemList">
                            <span class="left"  style="text-align: center;display:block; margin: 10px;"><i class="el-icon-coffee-cup"></i>标签</span>
                            <el-tag><i class="el-icon-price-tag" style="font-size:.8rem;margin-right:.5rem;"></i>学习</el-tag>
                            <el-tag><i class="el-icon-price-tag" style="font-size:.8rem;margin-right:.5rem;"></i>游戏</el-tag>
                        </div>
                        <br>
                        <div class="ItemList" >
                            <span class="left"  style="text-align: center;display:block;"><i class="el-icon-goblet"></i>热门博主</span>
                            <div style="
                                        margin-top: 20px;">
                                <div style="float:left">
                                    <el-avatar :size="30" :src="hotUser[0].userbaseinfo.headPortrait" style="margin-top: -5px"></el-avatar>
                                    <br>
                                    <el-avatar :size="30" :src="hotUser[1].userbaseinfo.headPortrait"></el-avatar>
                                </div>
                                <div >
                                    <i class="el-icon-user"></i><span style="font-size: 14px;color: #99a2aa;">{{hotUser[0].userbaseinfo.userName}}</span>
                                    <br><br>
                                    <i class="el-icon-user"></i><span style="font-size: 14px;color: #99a2aa;">{{hotUser[1].userbaseinfo.userName}}</span>
                                </div>
                            </div>
                        </div>
                        <div style="padding: 10px">
                            <br>
                            <el-button shadow="hover" type="success" plain style="padding: 20px 70px 20px 70px" @click="write">写博文</el-button>
                        </div>
                    </el-card>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<style scoped>
    .title-line{
        width: 100%;
        height: 1px;
        background: hsla(0,0%,59.2%,.21);
        margin-top:20px;
        margin-bottom: 20px;
    }
    #container{
        overflow-y: scroll;
    }
    html,body{width: 100%;height: 100%}
    *{margin: 0px;padding: 0px;}
	#headers{
		width: 100%;
		text-align: center;
		line-height: 50px;
	}
    #container{
        width: 100%;
        height: 600px;
    }

    .box-card2{
        width: 100%;
        height: 100%;
    }
    .img1{
        width:8rem;
        margin-right: 1.875rem;
        border-radius: .5rem;
        margin-top: 0px;
        overflow: hidden;
        cursor: pointer;}
    .img1:hover{
        transition: all .3s;
        position: relative;
        top: -0.1875rem;
        left: -0.09375rem;
        box-shadow: 0rem 5px 10px 3px #ccc;
    }
    .text{
        width: 32rem;
        min-height: 14rem;
        position: relative;}
    .title{
        display: block;
        margin-bottom: 1.25rem;
    }
    .desc{
        height: 6.25rem;
        font-size: 16px;
    }
    i{
        margin-right: 8px;
    }
    span{
        font-size: 16px;
        margin-left: .5rem;
        margin-right: .5rem;

    }

    .time {
        display: block;
    }
</style>

<script>
    export default {
        name: "BlogMain",
        data() {
            return {
                activeName: "first",
                // 总文章数
                count: 0,
                // 文章列表
                List: [

                ],
                // 每页数量
                pageSize:5,
                // 页数
                page: 1,
                // 点赞数量数组
                likes: [],
                // 标识是否为Category组件传来的
                isCategory: false,
                // 标识是否为Tag组件传来的
                isTag: false,
                posts:[],
                myblogs:[],
                param:{

                },
                hotUser:[{userbaseinfo:{}},{userbaseinfo:{}}],
                pageNum: 1,
                hotPost:[{},{}],
                page2:1,
                pageSize2:5,
                pageNum2:1,


            }
        },
        methods: {

            pageData(curr){
                this.pageNum=curr;
                this.findPost();
            },
            pageData2(curr2){
                this.pageNum2=curr2;
                this.findMyBlog();
            },
            findHotUser(){

                this.$axios
                    .post("FindBlogPost/findHotUser")
                    .then(rst=>{
                        this.hotUser=rst.data;

                        console.log(this.hotUser);

                    })
                    .catch(ex=>{
                        console.log(ex);
                    })

            },
            findHotPost(){

                this.$axios
                    .post("FindBlogPost/findHotPost")
                    .then(rst=>{
                        console.log(rst);
                        this.hotPost=rst.data;
                    })
                    .catch(ex=>{
                        console.log(ex);
                    })

            },
            write(){
                this.$router.push("/edit");
            },
            // 跳转详情页
            detailPage(articleId) {
                console.log(articleId);

                //浏览量+1
                var postView = [];
                postView.articleId = articleId;
                var viewParam = this.$qs.stringify(postView);
                this.$axios.post("post/changeViewCount",viewParam).then(rst=>{
                    console.log(rst.data);
                }).catch(ex=>{
                    console.log(ex);
                });

                this.$router.push({path:'/blogview',query:{articleId:articleId}});
            },
			mai(){
			    this.$router.push("/frontpage")			
			},
			edi(){
			    this.$router.push("/Edit")
			},
			uct(){
				this.$router.push("/userct")
			},
			fop(){
				this.$router.push("/Publish")
			},
			fm(){
				this.$router.push("/forumMain")		  
			},
			nct(){
				this.$router.push("/notict")				
			},
			blc(){
				this.$router.push("/BlogMain")				
			},
            findPost() {
                this.param.pageNum=this.pageNum;
                this.param.pageSize=this.pageSize;
                var postparam=this.$qs.stringify(this.param);

                this.$axios
                    .post("/FindBlogPost/findPost",postparam)
                    .then(rst=>{
                        this.posts=rst.data.list;
                        console.log(rst.data.list);
                        this.page=rst.data.total;
                        this.hotPost=rst.data.list;
                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
            },
            findMyBlog(){
                this.myblogs.pageNum2=this.pageNum2;
                this.myblogs.pageSize2=this.pageSize2;
                this.myblogs.userId=sessionStorage.getItem("userId");
                var postparam=this.$qs.stringify(this.myblogs);
                this.$axios
                    .post("/FindBlogPost/findMyBlog",postparam)
                    .then(rst=>{
                        this.myblogs=rst.data.list;
                        console.log(rst.data.list);
                        this.page2=rst.data.total;


                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
            }
        },
        mounted() {
            this.findPost();
            this.findHotUser();
            this.findHotPost();
            this.findMyBlog();

        },

    }
</script>

