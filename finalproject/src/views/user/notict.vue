<template>
	<div id="container">
		<div id='center'>
			<div id="left">
				<el-menu
						:router="true"
						:unique-opened="true"
						default-active="/follow">
					<el-menu-item index="/focusTrend">
						<i class="el-icon-s-cooperation"></i>
						关注动态</el-menu-item>
					<el-menu-item index="/focus">
						<i class="el-icon-connection"></i>
						我的关注</el-menu-item>
					<el-menu-item index="/follow">
						<i class="el-icon-magic-stick"></i>
						我的粉丝</el-menu-item>
					<el-menu-item  index="/marked">
						<i class="el-icon-s-management"></i>
						我的收藏</el-menu-item>
					<el-menu-item index="/modifieddata">
						<i class="el-icon-tickets"></i>
						修改资料</el-menu-item>
				</el-menu>
			</div>
			<div id="right">
				<router-view></router-view>
			</div>
		</div>
	</div>

</template>

<style scoped>
	html,body{width: 100%;}
	*{margin: 0px;padding: 0px;}
	#center{
		width: 100%;
		height: 100%;
	}
	#left{
		width: 200px;
		height: 100%;
	}
	#right{
		flex: 1;
		height: 100%;
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
	.el-radio-group{
		width: 100%;
		height: 100%;
	}
</style>

<script>
	export default {
		data() {
			return {
				tabPosition: 'left',
				activeIndex: '1',
			}
		},
		methods: {
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
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
			}
		}
	};
</script>