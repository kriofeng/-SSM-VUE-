<template>
    <div id="show_content">
        <div id="app">
            <!-- 用 v-for 渲染文章列表 -->
            <Article v-for="article in articleList" :key="article.title" :article="article"></Article>
        </div>
        <div id="page_content" >
            <el-pagination background
                           layout="prev,pager,next" :current-page="pageNum"
                           :total="page"
                           :page-size="pageSize"
                           @current-change="pageData"></el-pagination>
        </div>
    </div>
</template>

<script>
    import Article from '@/components/Article.vue';
    export default {
        name: "FocusTrend",
        components: {
            Article
        },
        data: function() {
            return {
                articleList: [

                ],
                page:1,
                pageNum:1,
                pageSize:4,
            };
        },
        methods:{
            pageData(curr){
                this.pageNum=curr;
                this.focusTrend();
            },
            focusTrend(){
                this.articleList.pageNum=this.pageNum;
                this.articleList.pageSize=this.pageSize;
                this.articleList.followerId=sessionStorage.getItem("userId");
                var postparam=this.$qs.stringify(this.articleList);
                this.$axios
                    .post("/post/focusTrend",postparam)
                    .then(rst=>{
                        this.articleList=rst.data.list;
                        console.log(rst.data.list);
                        this.page=rst.data.total;


                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
            }
        },
        created() {
            this.focusTrend();
        }
    }
</script>

<style scoped>
    #show_content{
        text-align: center;
        background-color: #fff;
        border-radius: 20px;
        /*width: 100%;
        height: 100%;
        margin-top: 300px;*/
        position: absolute;
        margin: auto;
        top: 30px;
        left: 0;
        right: 0;
        bottom: 0;
        height: 80%;
        width:  70%;
    }
</style>