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
                <h1 style="font-size:1.6rem">
                    <i class="iconfont icon-lianjie"></i>
                    标题1
                </h1>
                <br>
                <h5>
                <p>作者：XXX发布于2021-09-10更新</p>
                </h5>

                <br>
                <font size="4">
                    &#8194;&#8194;&#8194;&#8194;哈哈哈哈哈哈哈哈哈哈哈哈哈哈
                </font>
            </div>
            <div id="ple">
                <div class="app-container" id="pled">
                    <div class="complie">
                        <div class="el-input" style="width: 100%;margin-right: 10px;margin-bottom: 0.8rem;">
                            <input type="text" autocomplete="off" maxlength="500" placeholder="你想说些什么呢" class="el-input_inner" style="width: 1140px;height: 60px">
                            <button type="button" class="el-button el-button--info">
                                <span>发表</span>
                            </button>
                        </div>

                    </div>
                    <div class="contentItem">
                        <img src="" alt="用户头像">
                        <div class="info">
                            <div class="head">
                                <span class="name">hhhh</span>
                                <span class="number_id" style="color: rgba(100,106,101,0.91);">12 楼&#12288;&#12288;2021-08-09</span>


                            </div>
                            <div class="contentreview">

                                <span class="message" style="color: rgb(51,51,51);">&#12288;66666</span>
                                <span class="reply">回复</span>
                            </div>
                        </div>
                    </div>


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
        width:100%;
        height:40%;
        margin-left:10%;
        margin-top:35px;
    }
    .contentItem{
        font-size: 14px;
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        display: flex;
        border-bottom: 1px solid #d0d0d0;
        padding-top: 5px;
        padding-right: 8px;
    }
    .info{
        width: 1000px;
    }
    .number_id{
        font-size: 14px;
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        float: right;
        color: rgb(51, 51, 51);
    }
    .contentreview{
        font-size: 14px;
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        display: flex;
        justify-content: space-between;
    }
    .message{
        font-size: 14px;
        margin-top: 10px;
        padding: 0;
        box-sizing: border-box;
        color: rgb(51, 51, 51);
    }
    .reply{
        font-size: 14px;
        margin-right: -120px;
        margin-top: 10px;
        color: #2d8cf0;
        cursor: pointer;
    }
</style>
<script>
    export default {
        name: "BlogDetail",
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
			}
		}
    }
</script>

