<template>
	<div class="app-container">
		<div>
		<div class="titleInput">
			<el-input
					type="text"
					placeholder="请输入标题"
					v-model="title"
					maxlength="30" 
					show-word-limit
					style="width:800px;
					top:30px;
					left:50px;">
			</el-input>
		</div>

		<!--字数限制为30-->
		<div class="groupbtn" style="position: absolute;
										top:90px;
										right: 80px">
			<el-card>
				文章类型&nbsp;&nbsp;&nbsp;
			<el-radio-group v-model="checkType">
				<el-radio  label="1">学习</el-radio>
				<el-radio  label="2">生活</el-radio>
				<el-radio  label="3">科技</el-radio>
				<el-radio  label="4">游戏</el-radio>
			</el-radio-group>
			</el-card>
		</div>
		</div>

		<div class="mainInput">
			<editor :curValue="content" @input="newContent" style="width: 1400px; margin-left: 50px; margin-top:50px"/>
			<div style="position: absolute;
						right: 20px;
						bottom: 10px">
				<el-button type="primary" icon="el-icon-s-promotion" v-on:click="publishBlog()">发布</el-button>
			</div>

			</div>

		</div>
</template>
<script scoped>
	import { ElMessage } from 'element-plus'

	//引入组件
	const typeOptions = ['学习', '生活', '科技', '游戏'];
	import Tinymce from '@/components/tinymce/index'
	export default {
	//注册组件
		components: {
			'editor': Tinymce
		},

		methods: {
			publishBlog(){
				if(this.content === '' || this.title === '')
				{
					ElMessage.warning({
						message: '标题和内容不能为空',
						type: 'warning'
					});
				}else{
					this.$axios.post("/post/BlogCount").then(rst=>{
						this.articleId = rst.data+1;
						this.posts.articleId = "b"+this.articleId;
						this.posts.content = this.content;
						this.posts.title = this.title;
						this.posts.area = this.checkType;
						this.posts.type = "2";
						this.posts.userId = sessionStorage.getItem("userId");
						var _this = this;
						let yy = new Date().getFullYear();
						let mm = new Date().getMonth()+1;
						let dd = new Date().getDate();
						let hh = new Date().getHours();
						let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
						let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
						_this.gettime = yy+'/'+mm+'/'+dd+' '+hh+':'+mf+':'+ss;

						this.posts.date=_this.gettime;
						var param = this.$qs.stringify(this.posts);
						console.log(param);
						this.$axios.post("post/publishBlog",param).then(rst=>{
							if(rst.data === 1){
								ElMessage.success({
									message: '发布成功！',
									type: 'success'
								});
							this.$router.push("/blogMain");
							}else{
								ElMessage.warning({
									message: '发布失败！',
									type: 'warning'
								});
							}
						}).catch(ex=>{
							console.log(ex);
						});
					}).catch(ex=>{
						console.log(ex);
					});
				}
			},
			newContent(val) {
				console.log(val); //获取输入内容
				this.content = val;
			},
			back(){
			    this.$router.push("/userct")
			},
			returnPage(){
				if (window.history.length <= 1) {
				this.$router.push({ path: "/system/storageManagement" });
				return false;
				} else {
				this.$router.go(-1);
				}

			},
		},
		data() {
			return {
			posts:[],
			title: '',//标题
			checkType:'1',
			types: typeOptions,
			content:'',
			articleId:'',
		};
		}
	}
</script>

<Style	scoped>



</Style>
