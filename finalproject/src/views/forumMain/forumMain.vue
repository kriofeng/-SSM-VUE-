<template>
<div>
    <el-row id="Menu">
        <el-col :span="4">
                <el-menu default-active="0" id="labelMenu" class="el-menu-vertical-demo" @select="selectLabel" >
                    <el-menu-item v-for="(label,i) in labels" :index='String(i)' >
                        <i class="el-icon-setting"></i>
                        <template #title class="lb">{{label.labelContent}}</template>
                    </el-menu-item>
                </el-menu>
        </el-col>
        <el-col :span="16" >
            <div>
                <div id="title" style="padding: 20px; font-size: 40px">
                    {{labelName}}
                </div>
                <el-tabs type="border-card" @tab-click="handleClick" v-model="activeTab">
                    <el-tab-pane label="最新" name="first" value="1"></el-tab-pane>
                    <el-tab-pane label="热门" name="second"></el-tab-pane>
                    <el-tab-pane label="精华" name="third"></el-tab-pane>

                <div id="show_table">
                    <el-table :data="post" @row-click="detailsPage" class="mainTable"  row-style="cursor:pointer" >
                        <el-table-column :show-overflow-tooltip="true" label="标题" prop="title"></el-table-column>
                        <el-table-column label="楼主" prop="userbaseinfo.userName"></el-table-column>
                        <el-table-column label="发布时间" prop="date"></el-table-column>
                        <el-table-column label="回复数" prop="commentCount"></el-table-column>
                        <el-table-column label="点赞" prop="likedCount"></el-table-column>
                        <el-table-column label="精华" >
                            <template v-slot="scope">
                                {{scope.row.lableId==1?"精华":""}}
                            </template>
                        </el-table-column>
                    </el-table>
                </div>

                <div id="page_content" >
                    <el-pagination background
                                   layout="prev,pager,next" :current-page="pageNum"
                                   :total="pageTotal"
                                   :page-size="pageSize"
                                   @current-change="pageData"></el-pagination>
                </div>
                </el-tabs>
            </div>
        </el-col>
        <el-col :span="4">
            <div style="padding: 10px">
                <el-card shadow="hover" style="height: 300px;">
                    <div style=" font-size: 30px">{{notice.noticeTitle}}:</div>
                    <div style="margin-top: 20px">
                        {{notice.noticeContent}}
                    </div>
                </el-card>
                <br>
                <el-card shadow="hover" style="height: 300px;">
                    <div style="padding: 25px"><el-button type="primary" style="padding: 20px 60px 20px 60px" @click="publish">发帖</el-button></div>

                </el-card>
            </div>
        </el-col>
    </el-row>
</div>

</template>

<script>
    export default {
        name: "forumMain",
        data(){
            return{
                labels:[],
                label:'1',
                findPostInfo:{
                    area:''
                },
                post:[],
                pageNum:1,
                pageSize:3,
                pageTotal:0,
                labelName:"",
                activeTab:"first",
                tab:'1',
                currentpage:1,
                notice:{}
            }
        },
        methods:{
            getNotice(){
                this.$axios.post("notice/findNewNotice").then(rst=>{
                    this.notice = rst.data;
                }).catch(ex=>{
                    console.log(ex);
                });
            },
            detailsPage(row){
                console.log(row.articleId);
                //浏览量+1
                var postView = [];
                postView.articleId = row.articleId;
                var viewParam = this.$qs.stringify(postView);
                this.$axios.post("post/changeViewCount",viewParam).then(rst=>{
                    console.log(rst.data);
                }).catch(ex=>{
                    console.log(ex);
                });

                this.$router.push({path:'/ForumMainPost',query:{articleId:row.articleId}});
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
            publish(){
                this.$router.push("/Publish")
            },
            handleClick(tab, event) {
                this.pageNum=1;
                if(tab.props.label=="精华"){
                    this.tab='3';
                    console.log("点击了精华");

                    this.findPostBy("FindPost/postbygood");
                }
                if(tab.props.label=="热门"){
                    this.tab='2';
                    console.log("点击了热门");

                    this.findPostBy("FindPost/postbyheat");
                }
                if(tab.props.label=="最新"){
                    this.tab='1';
                    console.log("点击了最新");

                    this.findPost();
                }
            },
            findPostBy(url){
                this.findPostInfo.area=this.label;
                this.findPostInfo.pageNum=this.pageNum;
                this.findPostInfo.pageSize=this.pageSize;
                var param=this.$qs.stringify(this.findPostInfo);

                this.$axios
                    .post(url,param)
                    .then(rst=>{
                        this.post=rst.data.list;
                        console.log(this.post);
                        this.pageTotal=rst.data.total;
                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
            },
            findPost(){
                this.findPostInfo.area=this.label;
                this.findPostInfo.pageNum=this.pageNum;
                this.findPostInfo.pageSize=this.pageSize;
                var param=this.$qs.stringify(this.findPostInfo);
                console.log(param);
                this.$axios
                    .post("FindPost/post",param)
                    .then(rst=>{
                        this.post=rst.data.list;
                        console.log(this.post);
                        this.pageTotal=rst.data.total;
                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
            },
            handleOpen(key,keypath){
                console.log(key.index);
            },
            getLabel(){

            },
            selectLabel(key,keypath){
                this.pageNum=1;
                this.labelName=this.labels[key].labelContent;
                this.label=this.labels[key].labelId;
                console.log(this.label);
                this.findPost();
                this.activeTab="first";
            },
            pageData(curr){
                this.pageNum=curr;
                if(this.tab=="1"){console.log("page ----");
                    this.findPost();

                }
                if(this.tab=="2"){ console.log("page ----热度");
                    this.findPostBy("FindPost/postbyheat");

                }
                if(this.tab=="3"){console.log("page ----精华");
                    this.findPostBy("FindPost/postbygood");
                }

            }

        },
        created() {
            this.$axios
                .post("AllLabel/findLabel")
                .then(rst=>{
                    this.labels=rst.data;
                    console.log(this.labels);
                    this.labelName=this.labels[0].labelContent;
                })
                .catch(ex=>{
                    console.log(ex);
                })
            this.findPost();
            this.getNotice();
            // this.labelName=this.labels[0].labelContent;

        }
    }
</script>

<style scoped>
	#headers{
		width: 100%;
		text-align: center;
		line-height: 50px;
	}
    router-link{
        text-decoration: none;

    }
    #Menu{
        height: 200px;

    }
    #labelMenu{
        min-height: 660px;
        width: 200px;

    }
    .lb{
        font-family: 'PingFang SC';
    }
    template{
        height: 100%;
    }

</style>