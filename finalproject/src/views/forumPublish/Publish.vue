<template>
    <div id="info">
		<br>
    <el-row>
        <el-col :span="7" id="user" >
            <el-card shadow="hover" id="card">
                <el-row >
                    <div >
                        <el-avatar :size="50" :src="headPortrait"></el-avatar>
                    </div>
                </el-row>
                <el-row :span="20">
                    <div >用户名：{{userName}}</div>
                </el-row>
                <el-row :span="20">
                    <div >用户id：{{userId}}</div>
                </el-row>
                <el-row :span="20">
                    <div>板块：<el-select v-model="label" placeholder="请选择" size="mini" >
                        <el-option
                                v-for="label in labels"
                                :key="label.labelId"
                                :label="label.labelContent"
                                :value="label.labelId">
                        </el-option>
                    </el-select>
                    </div>
                </el-row>
            </el-card>
        </el-col>
        <el-col :span="17" id="tips" style="color: #6D737B;">
            1、用户的言行不得违反《计算机信息网络国际联网安全保护管理办法》、《互联网信息服务管理办法》、《互联网
			<br>
			电子公告服务管理规定》、《维护互联网安全的决定》、《互联网新闻信息服务管理规定》等相关法律规定。
            <br>
            2、不能在论坛中发表含有猥亵、色情、人身攻击和反政府言论等非法或侵权言论的信息；
            <br>
            3、不能在论坛发表不符合版面主题，或者无内容的灌水文章；
            <br>
            4、不能在论坛中发表含有猥亵、色情、人身攻击和反政府言论等非法或侵权言论的信息；
			<br>
        </el-col>
    </el-row>
        <el-row>
            <el-input  v-model="title"  style="padding: 10px  0 0 100px;; width: 500px">
                <template #prepend>标题</template>
            </el-input>
        </el-row>
    </div>
    <el-container id="main">
        <el-main id="editor" class="editor">
            <editor  :curValue="content" @input="newContent" />
        </el-main>
        <el-footer id="bottom" class="bottom" style="background-color: #FFFFFF;">
            <el-row justify="end">
				<el-button type="primary" style="margin-left: 1210px;"  round @click="pub()">提交</el-button></el-row>
        </el-footer>
    </el-container>
</template>


<script scoped>
    import Tinymce from '../../components/tinymce/indexForum'
    import { h } from 'vue';

    export default {
        name: "Publish",
        data() {
            return {
                param:{
                    userId:'',
                    title:'',
                    content:'',
                    date:'',
                    articleId:'',
                    area:''
                },
                userName:'',
                userId:'',
                articleId: '',
                section:'test',
                content:'',
                date:'',
                title:'',
                labels:[],
                label:'1',
                headPortrait:'',
                posts:[]
            }
        },
        components: {
            'editor': Tinymce
        },
        methods: {
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
            findId(){
                this.$axios
                    .post("ForumId/findId")
                    .then(rst=>{
                        var str="p"+(rst.data+1);
                        console.log(str);
                        this.articleId=str;

                    }).catch(ex=>{
                    console.log(ex);
                });
            },
            open1() {
                this.$notify({
                    title: '成功',
                    message: '发布成功',
                    type: 'success'
                });
                this.content = '';
                this.title = '';
                this.init();
            },

            open2() {
                this.$notify({
                    title: '警告',
                    message: '发布失败',
                    type: 'error'
                });
            },
            open3() {
                this.$notify({
                    title: '警告',
                    message: '请选择发布版区',
                    type: 'warning'
                });
            },
            pub(){
                if(this.label==''){
                    this.open3();
                }else{
                    this.getCurrentTime();
                    this.param.userId=this.userId;
                    this.param.title=this.title;
                    this.param.content=this.content;
                    this.param.date=this.date;
                    this.param.articleId=this.articleId;
                    this.param.area=this.label;
                    console.log(this.param);
                    var poinfo=this.$qs.stringify(this.param);
                    this.$axios
                        .post("forumPublish/publish",poinfo)
                        .then(rst=>{
                            console.log(rst);
                            if(rst.data==1){
                                this.open1();

                            }else{
                                this.open2();
                            }

                        }).catch(ex=>{
                        this.open2();
                        console.log(ex);
                    });
                }

            },
            newContent(val) {
                this.content=val;
                console.log(val); //获取输入内容
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
            returnPage(){
                if (window.history.length <= 1) {
                    this.$router.push({ path: "/system/storageManagement" });
                    return false;
                } else {
                    this.$router.go(-1);
                }
            },
            init(){
                this.posts.userId = sessionStorage.getItem("userId");
                var param = this.$qs.stringify(this.posts);
                this.$axios.post("user/findUser",param).then(rst=>{
                    console.log(rst);
                    if(rst.data != null){
                        this.userId = rst.data.userId;
                        this.headPortrait = rst.data.headPortrait;
                        this.userName = rst.data.userName;
                    }
                }).catch(ex=>{
                    console.log(ex);
                });
            }
        },
        mounted: function() {
            this.findId();
        },
        created() {
            this.$axios
            .post("AllLabel/findLabel")
            .then(rst=>{
                this.labels=rst.data;
                console.log(this.labels);

            })
            .catch(ex=>{
                console.log(ex);
            });
            this.init();
        }
    }

</script>

<style scoped>
	#headers{
		width: 100%;
		text-align: center;
		line-height: 50px;
	}
    #tips{
        font-size: 16px;
        padding: 5px 20px 0 50px;
    }
    #card{
        background-color: #ecf5ff;
    }
    #editor{
        margin: 0 auto;
        width: 90%;
    }
    #user{

        padding: 0  0 0 100px;
    }
    #headpic{
        border-radius:50%;
        background-color: #ffffff;
    }
    #main{
        background: #ffffff;
        height: 381px;
        width: 100%;
    }
    #info{
        height: 243px;
        background: #ffffff;

    }
</style>