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
				<div style="margin-left: 1000px;">
					<table style="float: left;">
						<tr>
							<td>
								<el-link v-on:click="nct" style="color: white;">&#8194;&#8194;消息中心&#8194;</el-link>							
							</td>
							<td>
								<el-link v-on:click="uct" style="color: white;">&#8194;&#8194;个人中心&#8194;</el-link>								
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
		<div id='center'>
			<div id='left'>
				 <div class="block" align="center" style="margin-top: 40px;">
					 <el-avatar :size="80" :src="circleUrl"><!--头像-->		
					</el-avatar>
				 </div>
				 <div v-text="msg"  align="center" style="margin-top: 10px; color: #F5F2F0;"></div><!--用户名-->	
				<table style="color: #F5F2F0; margin-top: 10px;">
				 <tr>
				 	<td>
						<div style="margin-left: 60px;">
							关注：
						</div>	 							
				 	</td>
					 <td>
						  <div v-text="msg2"></div>
					 </td>					  
				 </tr>
				 <tr>
				 	<td>
						<div style="margin-left: 60px;">
							粉丝：
						</div>	 							
				 	</td>
				 	<td>
						<div v-text="msg3"></div>
				 	</td>				  
				 </tr>
				 <tr>
				 	<td>
						<div style="margin-left: 59px;">
							博客数：
						</div>	 							
				 	</td>
					 <td>
						  <div v-text="msg4"></div>
					 </td>
					  
				 </tr>					
				</table>

			</div>
			<div id='right'>
				<div>
					<div class="block">
					  <el-timeline>
					    <el-timeline-item timestamp="2018/4/12" placement="top">
					      <el-card>
					        <h4>更新 Github 模板</h4>
					        <p>王小虎 提交于 2018/4/12 20:46</p>
					      </el-card>
					    </el-timeline-item>
					    <el-timeline-item timestamp="2018/4/3" placement="top">
					      <el-card>
					        <h4>更新 Github 模板</h4>
					        <p>王小虎 提交于 2018/4/3 20:46</p>
					      </el-card>
					    </el-timeline-item>
					    <el-timeline-item timestamp="2018/4/2" placement="top">
					      <el-card>
					        <h4>更新 Github 模板</h4>
					        <p>王小虎 提交于 2018/4/2 20:46</p>
					      </el-card>
					    </el-timeline-item>
					  </el-timeline>
					</div>
				</div>
			</div>			
		</div>
	</div>
</template>

<style scoped>
	html,body{width: 100%;}
	*{margin: 0px;padding: 0px;}	
	#headers{
		width: 100%;
		text-align: center;
		line-height: 50px;
	}
	#container{
		width: 100%;
		height: 90%;
	}
	#center{
		width: 100%;
		flex: 1;
		display: flex;
	}
	#left{
		position:absolute;
		width: 15%;
		height: 90%;
        background: rgba(0, 0, 0, 0.5);
		box-shadow: 0px 15px 25px rgba(0,0,0,.5);
		border-radius:15px;
	}
	#right{
		margin-top: 50px;
		margin-left: 280px;
		width: 70%;
		height: 100%;
		background-color:#fcfcfc;
	}
	.el-radio-group{
		width: 100%;
		height: 100%;
	}
	  .el-dropdown-link {
	    cursor: pointer;
	    color: #409EFF;
	  }
	  .el-icon-arrow-down {
	    font-size: 12px;
	  }
</style>

<script>
  export default {
    data () {
      return {
		msg:"用户名",
		msg2:"20",
		msg3:"10",
		msg4:"3",
        circleUrl: ""//服务器地址
      }
    },
	methods:{
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
		}
	}
  }
</script>