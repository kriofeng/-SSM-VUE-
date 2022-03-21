<template>
    <div class="article">
        <div class="article-cover">
            <img :src="'@/assets/img/img.jpg'" alt="">
        </div>
        <div class="info">
            <div class="article-title " style="margin-bottom: -10px">{{ article.userbaseinfo.userName }}</div>
            <div class="article-userName">{{ article.title }}</div>
            <div class="article-time">{{ article.date }}</div>
            <div class="article-content"><i class="el-icon-view"></i>浏览量{{ article.views }}&#12288;&#12288;&#12288;
                <i class="el-icon-star-on"></i>点赞量{{ article.likedCount }}&#12288;&#12288;&#12288;
                <i class="el-icon-collection-tag"></i>收藏数{{ article.markedCount }}&#12288;&#12288;&#12288;
                <i class="el-icon-chat-dot-round"></i>评论数{{ article.commentCount }}&#12288;&#12288;&#12288;</div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Article",
        props: {
            article: Object
        },
        // 计算属性
        computed: {
            content: function() {
                return this.article.content;
            }
        }
    }
</script>

<style scoped>
    .article {
        width: 4000px;
        display: flex;
        padding: 10px 0;
        border-bottom: 1px solid #dbdbdb;}
    .article-title {
        font-size: 16px;
        font-weight: 500;
        color: #494f48;
        text-align: justify;
    }
    .article-cover {
        flex: none;
        width: 120px;
        margin-right: 10px;}

    .article-content, .article-time {
        font-size: 14px;
    }
    .article-content{
        color: #aaa69d;
    }
    .article-userName{
        width: 100%;
        height: 20px;
        color: #c5adb4;
        margin-top: 10px;
    }
    .article-time {
        margin: 10px 0;
        color: #c5c5c5;
    }

</style>