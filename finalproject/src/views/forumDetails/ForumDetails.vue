<template>
	<div id="container">
	<div id="headers">
		<el-menu
			:default-active="activeIndex"
			class="el-menu-demo"
			mode="horizontal"
			@select="handleSelect"
			background-color="#545c64"
			text-color="#fff"
			active-text-color="#ffd04b">
				<el-menu-item v-on:click="mai" index="1" style="width: 100px; margin-left: 20px;">首页</el-menu-item>
				<el-menu-item v-on:click="blc" index="2" style="width: 100px;">博客</el-menu-item>
				<el-menu-item v-on:click="fm" index="3" style="width: 100px;" >论坛</el-menu-item>
				<div style="margin-left: 1100px;">
					<table style="float: left;">
						<tr>
							<td>
								<el-link v-on:click="nct" style="color: white;">&#8194;&#8194;消息中心&#8194;</el-link>							
							</td>
							<td>
								<el-link v-on:click="edi" style="color: white;">&#8194;&#8194;博客创作&#8194;</el-link>
							</td>
							<td>
								<el-link v-on:click="fop" style="color: white;">&#8194;&#8194;论坛创作&#8194;&#8194;</el-link>
							</td>
							<td>
								<div style="margin-top: 5px; margin-left: 5px;">
								<el-avatar :size="35" :src="circleUrl"><!--头像--></el-avatar>											
								</div>
					
							</td>
						</tr>
					</table>
				</div>
			</el-menu>
	</div>
		<div id="fdcenter">
			<div id="page">
				<br>
				<font size="6">
					&#8194;&#8194;讨论内容标题					
				</font>
				<br>
				<br>
				<font size="4">
					&#8194;&#8194;&#8194;&#8194;床头明月光疑是地上霜				
				</font>
			</div>
			<div id="ple">
				<div class="app-container" id="pled">
					<editor :curValue="content" @input="newContent" />
				</div>
				<div id="tjbtn">
					<el-button type="primary" round @click="pub()">评论</el-button>					
				</div>
			</div> 
		</div>
	</div>
</template>

<style scoped>
	#fdcenter{
		width:100%;
		height:100%;
		
	}
	#page{
		width:80%;
		height:50%;
		margin-left:10%;
		box-shadow: 0px 10px 15px rgba(0,0,0,.5);
		background: rgba(240, 240, 232, 0.5);
	}
	#ple{
		width:100%;
		height:50%;
	}
	#headers{
		width: 100%;
		text-align: center;
		line-height: 50px;
	}
	#container{
		width: 100%;
		height: 100%;
	}
	#pled{
		width:70%;
		height:40%;
		margin-left:10%;
		margin-top:35px;
	}
	#tjbtn{
		margin-left:1150px;
		margin-top:45px;
	}
</style>

<script>
  import Tinymce from '@/components/tinymce/index'
  export default {
	components: {
	  	'editor': Tinymce
	},
	data () {
	    return {
	        count: 0,
			content:''
	    }
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
	  load () {
	    this.count += 2
	  },
	  newContent(val) {
	  	console.log(val); //获取输入内容
	  },
	  pub(){
	      this.getCurrentTime();
	      this.param.userId=this.userId;
	      this.param.title=this.title;
	      this.param.content=this.content;
	      this.param.date=this.date;
	      this.param.articleId=this.articleId;
	      var poinfo=this.$qs.stringify(this.param);
	      this.$axios
	          .post("forumDetails/ForumDetails",poinfo)
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
	}
  };
  </script>