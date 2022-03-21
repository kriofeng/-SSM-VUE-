import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import AdminLogin from "../views/adminLogin/AdminLogin";
import AdminPostControl from "../views/adminPostControl/AdminPostControl";
import Login from "../views/login/Login";
import Login_Reg from "../views/login_reg/Login_Reg";
import Main from "../views/main/Main";
import Publish from "../views/forumPublish/Publish";
import Notict from "../views/user/notict.vue";
import Userct from "../views/user/userct.vue";
import ArticleAudit from "../views/articleAudit/ArticleAudit";
import RetrievePassword from "../views/retrievePassword/RetrievePassword";
import Follow from "../views/follow/Follow";
import forumMain from "../views/forumMain/forumMain";
import Edit from "../views/Edit";
import ForumDetails from "../views/forumDetails/ForumDetails";
import Frontpage from "../views/frontpage";
import BlogMain from "../views/blogMain/BlogMain";
import BlogDetail from "../views/blogDetail/BlogDetail";
import AdminBlogControl from "../views/adminBlogControl/AdminBlogControl";
import Focus from "../views/focus/Focus";
import ForumMainPost from "../views/forumMainPost/ForumMainPost";
import marked from "../views/marked/marked";
import BlogView from "../views/BlogView/BlogView";
import Test from "../views/test/Test";
import modifieddata from "../views/modifieddata/modifieddata";
import adminHome from "../views/adminHome";
import Test2 from "../views/test2/Test2";
import FocusTrend from "../views/focusTrend/FocusTrend";

const routes = [
  {
    path:'/',
    redirect: '/login'
  },
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [
      {
        path: "/dashboard",
        name: "dashboard",
        meta: {
          title: '系统首页'
        },
        component: () => import ( /* webpackChunkName: "dashboard" */ "../views/Dashboard.vue")
      },
      {
        path:"/adminChart",
        name:"AdminChart",
        meta:{
          title:"图表统计"
        },
        component:()=>import("../views/adminChart/AdminChart")
      },
      {
        path:'/adminUserControl',
        name:'AdminUserControl',
        meta:{
          title:'用户管理'
        },
        component:()=>import(/* webpackChunkName: "adminUserControl" */"../views/adminUserControl/AdminUserControl")
      },
      {
        path: "/adminBlogControl",
        name: "/AdminBlogControl",
        meta: {
          title: '博客管理'
        },
        component: AdminBlogControl
      },  {
        path:'/adminPostControl',
        name:'/AdminPostControl',
        meta: {
          title: '帖子表格'
        },
        component:AdminPostControl
      },
    ]
  },
  {
    path:'/test',
    name:'/Test',
    component:Test
  },
  {
    path:'/test2',
    name:'/Test2',
    component:Test2
  },
  {
    path:'/blogDetail',
    name:'/BlogDetail',
    component:BlogDetail,
    meta:{
      loginRequired:true
    }
  },
  {
    path:'/adminLogin',
    name:'AdminLogin',
    component:AdminLogin
  },
  {
    path:'/retrievePassword',
    name:'RetrievePassword',
    component:RetrievePassword
  },
  {
    path:'/login',
    name:"Login",
    component:Login
  },
  {
    path:'/login_reg',
    name:'Login_Reg',
    component:Login_Reg
  },
  {
    path:'/blogDetail',
    name:'BlogDetail',
    component:BlogDetail,
    meta:{
      loginRequired:true
    }
  },

  {
    path:'/main',
    name:'Main',
    component:Main,
    meta:{
      loginRequired:true
    }
  },
  {
    path:'/articleAudit',
    name:'ArticleAudit',
    component:ArticleAudit
  },
  {
    path:'/userct',
    name:'/Usect',
    component:Userct,
    meta:{
      loginRequired:true
    }
  },

  {
    path:'/forumDetails',
    name:'ForumDetails',
    component:ForumDetails,
    meta:{
      loginRequired:true
    }
  },

  {
    path:'/publish',
    name:'Publish',
    component:Publish
  },
  {
    path:'/notict',
    name:'/Notict',
    component:Notict,
    children:[
      {
        path: '/follow',
        name: 'Follow',
        component:Follow
      },
      {
        path: '/focus',
        name: 'Focus',
        component:Focus
      }
    ]
  },
  {
    path:'/userct',
    name:'/Usect',
    component:Userct
  },
  {
    path:'/forumMain',
    name:'ForumMain',
    component:forumMain
  },
  {
	  path:'/forumDetails',
	  name:'ForumDetails',
	  component:ForumDetails
  },
  {
    path:'/frontpage',
    name:'Frontpage',
    component:Frontpage
  },
  {
    path:'/adminhome',
    name:'/adminhome',
    component:adminHome,
    meta:{
      loginRequired:true
    },
    children:[
      {
        path:'/blogMain',
        name:'BlogMain',
        component:BlogMain,
        meta:{
          loginRequired:true
        }
      },
      {
        path:'/frontpage',
        name:'Frontpage',
        component:Frontpage,
        meta:{
          loginRequired:true
        },
      },
      {
        path:'/forumMain',
        name:'ForumMain',
        component:forumMain,
        meta:{
          loginRequired:true
        }
      },
      {
        path:'/notict',
        name:'/Notict',
        component:Notict,
        redirect:'/follow',
        meta:{
          loginRequired:true
        },
        children:[
          {
            path: '/focusTrend',
            name: 'FocusTrend',
            component:FocusTrend,
            meta:{
              loginRequired:true
            }
          },
          {
            path: '/follow',
            name: 'Follow',
            component:Follow,
            meta:{
              loginRequired:true
            }
          },
          {
            path: '/focus',
            name: 'Focus',
            component:Focus,
            meta:{
              loginRequired:true
            }
          },
          {
            path:'/marked',
            name:'marked',
            component:marked,
            meta:{
              loginRequired:true
            }
          },
          {
            path:'/modifieddata',
            name:'modifieddata',
            component:modifieddata,
            meta:{
              loginRequired:true
            }
          }
        ]
      },
      {
        path:'/publish',
        name:'Publish',
        component:Publish,
        meta:{
          loginRequired:true
        }
      },
      {
        path: '/edit',
        name: 'Edit',
        component:Edit,
        meta:{
          loginRequired:true
        }
      },
      {
        path:'/BlogView',
        name:'/BlogView',
        component:BlogView,
        meta:{
          loginRequired:true
        }
      },
      {
        path:'/forumMainPost',
        name:'ForumMainPost',
        component:ForumMainPost
      },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

router.beforeEach((to, from, next) => {
  if(to.matched.some((item)=>{
    return item.meta.loginRequired
  })){
    if((sessionStorage.getItem("userId"))){
      next()
    }else{
      next({
        path:'/login',
        query:{redirect:to.fullPath}
      })
    }
  }else{
    next()
  }
})