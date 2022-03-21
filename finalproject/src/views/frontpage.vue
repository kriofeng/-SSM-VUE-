<template>
	<div id="headers">
		<!-- 跑马灯  -->
		<div>
			<el-carousel :interval="4000" type="card" height="200px">
				<el-carousel-item v-for="item in imgList" :key="item.id">
					<img :src="item.idView" class="image">
				</el-carousel-item>
			</el-carousel>
		</div>			
		<div  class="font_div">
				平台特点			
		</div>
		<div  class="font_text">
				中文互联网高质量的问答社区和创作者聚集的原创内容平台
				独特的知识交流社区			
		</div>
		<div id="pages">
			<el-tabs v-model="activeName" @tab-click="handleClick">
			<el-tab-pane label="便捷博客发布" name="first" >
				<div>
					便捷博客 论坛一键式发布 畅所欲言
					<img src="">
				</div>
			</el-tab-pane>
			<el-tab-pane label="知名大V入驻" name="second">
				凭借认真、专业、友善的社区氛围和易获得的优质内容
				<br>
				吸引了中文互联网科技、商业、文化领域最具创造力的人群
			</el-tab-pane>
			<el-tab-pane label="优质博客加精" name="third">
				优质博客置顶加精
				<br>
				让更多人看到优质内容
			</el-tab-pane>
			<el-tab-pane label="高质量问答内容" name="fourth">
				对于发散思维的整合，是一大特色
				<br>
				鼓励在问答过程中进行讨论，以拓宽问题的发散性
				<br>
				鼓励答案的非针对性，鼓励答案的可参考性
			</el-tab-pane>
			</el-tabs>
			<img src="../assets/图片1.png">
		</div>
	</div>

</template>

<style scoped>
	#headers{
		width: 100%;
		text-align: center;
		line-height: 50px;
	}
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
	line-width: 200px;
    margin: 0;
  }
  #pagess{
  	width:100%;
  	display:flex;
  	margin-top: 2%;
  }
  #pages{
	width:80%;
	display:flex;
	margin-left: 25%;
  }
  .font_div{
	margin-top: 10px;
  	font-weight:bold;/*加粗*/
  	text-align:left;
	margin-left: 25%;
	font-size: 30px;
	}
	.font_text{
		margin-top: 10px;
		text-align:center;   /* 水平居中 */
		font-size: 20px;
		}
</style>

<script>
export default {
    name: 'index',
    data(){
        return {
            imgList: [
                {id:0,idView:require('../assets/imgList/bfclogo.jpg')},
				{id:1,idView:require('../assets/imgList/1.jpg')},
				{id:2,idView:require('../assets/imgList/2.jpg')},
				{id:3,idView:require('../assets/imgList/3.jpg')},
				{id:4,idView:require('../assets/imgList/4.jpg')},
            ],
			activeName: 'second'
        };
        },
	methods: {
		handleClick(tab, event) {
		    console.log(tab, event);
		}
    }
}
</script>