<template>
	<div id="bottom">
            <div id="header">
				<table style="margin-left: 20px;">
					<tr>
						<td>
							<div style="margin-left: 10px; margin-top: 10px; color: #fcfcfc;">管理员：{{adminName}}&#8194;您好！</div>							
						</td>
						<td>
							<div  style="margin-left: 1100px; margin-top: 7PX;">
							<el-button v-on:click="back" icon="el-icon-back">返回</el-button>
							</div>
						</td>
					</tr>					
				</table>
            </div>
			<table style="float: left; margin-left:80px;">
				<tr>
					<td>
						<div class="user"  style="margin-top: 40px;" align="left"><el-avatar :size="40" :src="circleUrl"></el-avatar></div>							
					</td>
						<td>
						<div class="user" style="margin-left: 10px; margin-top: 50px; color: #808080;">用户id：{{user.userId}}</div>								
					</td>
					<td>
						<div class="user" style="margin-left: 20px; margin-top: 50px; color: #808080;">用户名：{{user.userName}}</div>							
					</td>
				</tr>
			</table>
            <div class="demo-btn" style="float: right; margin-top: 15px; margin-right: 110px;">
                <el-button type="danger" round class="btn1" plain>&#8194;加精&#8194;</el-button>
                <el-button type="success" round style=" margin: 10px;" class="btn2" plain>&#8194;置顶&#8194;</el-button>
            </div>
            <div class="demo" style="margin-right: 80px; margin-left: 80px; margin-bottom: 20px; margin-top: 90px;">
                <el-input
                    type="textarea"
                    id="input1"
                    :rows="21"
                    v-model="textarea"
                    readonly="true">
				</el-input>
            </div>
            <div class="btn" style="margin-left: 600px;">
                <el-button type="success" round class="btn1" >&#8194;通过&#8194;</el-button>
                <el-button type="warning" round class="btn2">&#8194;驳回&#8194;</el-button>
            </div>
			<table id="tb_2" align="right" style="margin-right: 75px;">
				<tr>
					<td>
						<div class="user" style="margin-left: 800px; color: #808080;">发布时间：{{user.date}}</div>						
					</td>
					<td>
						<div class="user" style="margin-left: 10px; color: #808080;">标签：{{user.lableId}}</div>									
					</td>
					<td>
						<div class="user" style="margin-left: 10px; color: #808080;">浏览量{{user.views}}</div>								
					</td>	
				</tr>
			</table>
    </div>
</template>

<script>
    import { defineComponent, ref } from 'vue'
    export default defineComponent({
        setup() {
            return {
                textarea: 'hhhhhhhhhhhhhhh'
            }
        },
        name: "ArticleAudit",
        data:function () {
            return{
                user:{
                    headPortrait:'',
                    userId:'222',
                    userName:'用户名',
                    date:'2023-09-22',
                    lableId:'游戏',
                    content:'aaaaaaaaaaaaaaaaaaaa',
                    views:10
                },
				adminName:"张三",
				circleUrl:""
            }
        },
        methods:{
            reback(){
                this.$router.push("/adminPostControl")
            }
        }
    })
</script>

<style scoped>
	#bottom{
		width: 100%;
		height: 100%;
        background: rgba(255, 250, 232, 0.6);		
	}
    #header{
        width: 100%;
        height: 10%;
        background: rgba(56, 56, 56, 0.6);
    }
    .user{
        font-size: 16px;
    }
    .demo /deep/ #input1 {
        min-height: 30px;
        margin: 30px;
        width: 900px;
        height: 350px;
    }
    .btn{
        position: fixed;
    }
</style>