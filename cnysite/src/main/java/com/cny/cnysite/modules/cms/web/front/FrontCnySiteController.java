//package com.cny.cnysite.modules.cms.web.front;
//
//import com.cny.cnysite.common.config.Global;
//import com.cny.cnysite.common.persistence.Page;
//import com.cny.cnysite.common.servlet.ValidateCodeServlet;
//import com.cny.cnysite.common.utils.StringUtils;
//import com.cny.cnysite.common.web.BaseController;
//import com.cny.cnysite.modules.cms.entity.*;
//import com.cny.cnysite.modules.cms.service.*;
//import com.cny.cnysite.modules.cms.utils.CmsUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//@RequestMapping("${frontPath}")
//public class FrontCnySiteController extends BaseController{
//    @Autowired
//    private SiteService siteService;
//    @Autowired
//    private ArticleDataService articleDataService;
//    @Autowired
//    private ArticleService articleService;
//    @Autowired
//    private CategoryService categoryService;
//    @Autowired
//    private LinkService linkService;
//    @Autowired
//    private CommentService commentService;
//    /*首页*/
//    @RequestMapping
//    public String index(Model model){
//        Site site = siteService.get(Site.getCurrentSiteId());
//        model.addAttribute("site", site);
//        model.addAttribute("isIndex", true);
//        if(StringUtils.isNotBlank(site.getCustomIndexView())){
//            return "modules/cms/front/themes/"+site.getTheme()+"/frontIndex"+site.getCustomIndexView();
//        }
//        return "modules/cms/front/themes/"+site.getTheme()+"/frontIndex";
//    }
//    @RequestMapping("index-{siteId}${urlSuffix}")
//    public String index(@PathVariable String siteId, Model model){
//        Site site = siteService.get(siteId);
//        model.addAttribute("site", site);
//        model.addAttribute("isIndex", true);
//        if(StringUtils.isNotBlank(site.getCustomIndexView())){
//            return "modules/cms/front/themes/"+site.getTheme()+"/frontIndex"+site.getCustomIndexView();
//        }else{
//            List<Category> mainNavList = CmsUtils.getMainNavList(siteId);
//            if (mainNavList.size()>0) {
//                String firstCategoryId = mainNavList.get(0).getId();
//                return "redirect:" + Global.getFrontPath() + "/list-" + firstCategoryId + Global.getUrlSuffix();
//            }else{
//                model.addAttribute("site", site);
//                return "modules/cms/front/themes/"+site.getTheme()+"/frontListCategory";
//            }
//        }
//    }
//
//    @RequestMapping("list-{categoryId}${urlSuffix}")
//    public String list(@PathVariable String categoryId, @RequestParam(required = false,defaultValue = "1") Integer pageNo,
//                       @RequestParam(required = false,defaultValue = "15") Integer pageSize, Model model) {
//        Category category = CmsUtils.getCategory(categoryId);
//        if (category == null) {
//            return "error/404";
//        }
//        Site site = CmsUtils.getSite(category.getSite().getId());
//        model.addAttribute("site", site);
//        if ("2".equals(category.getShowModes()) && "article".equals(category.getModule())) {
//            //获取兄弟栏目列表（当为二级菜单时则只显示当前栏目）
//            List<Category> categoryList = new ArrayList<Category>();
//            if ("1".equals(category.getParent().getId())) {
//                categoryList.add(category);
//            } else {
//                categoryList=CmsUtils.getCategoryList(category.getSite().getId(),category.getParentId(),10,null);//cny_overwrite
//            }
//            model.addAttribute("category", category);
//            model.addAttribute("categoryList", categoryList);
//            //获取当前栏目下的第一篇文章
//            List<Article> articleList = CmsUtils.getArticleList(category.getSite().getId(), categoryId, 1, null);//cny_overwrite
//            if (articleList.size() > 0) {
//                articleList.get(0).setArticleData(articleDataService.get(articleList.get(0).getId()));
//                articleService.updateHitsAddOne(articleList.get(0).getId());
//                model.addAttribute("article", articleList.get(0));
//            }
//            CmsUtils.addViewConfigAttribute(model,category);
//            CmsUtils.addViewConfigAttribute(model,articleList.get(0).getViewConfig());
//            return "modules/cms/front/themes/" + site.getTheme() + "/" + getTpl(articleList.get(0));//frontViewArticle
//        }else{
//            List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
//            // 展现方式为1 、无子栏目或公共模型，显示栏目内容列表
//            if("1".equals(category.getShowModes())||categoryList.size()==0){
//                // 有子栏目并展现方式为1，则获取第一个子栏目；无子栏目，则获取同级分类列表。
//                if(categoryList.size()>0){
//                    category = categoryList.get(0);
//                }else{
//                    // 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
//                    if (category.getParent().getId().equals("1")){
//                        categoryList.add(category);
//                    }else{
//                        categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
//                    }
//                }
//                model.addAttribute("category", category);
//                model.addAttribute("categoryList", categoryList);
//                // 获取内容列表
//                if ("article".equals(category.getModule())){
//                    Page<Article> page = new Page<Article>(pageNo, pageSize);
//                    //System.out.println(page.getPageNo());
//                    page = articleService.findPage(page, new Article(category), false);
//                    model.addAttribute("page", page);
//                    // 如果第一个子栏目为简介类栏目，则获取该栏目第一篇文章
//                    if ("2".equals(category.getShowModes())){
//                        Article article = new Article(category);
//                        if (page.getList().size()>0){
//                            article = page.getList().get(0);
//                            article.setArticleData(articleDataService.get(article.getId()));
//                            articleService.updateHitsAddOne(article.getId());
//                        }
//                        model.addAttribute("article", article);
//                        CmsUtils.addViewConfigAttribute(model, category);
//                        CmsUtils.addViewConfigAttribute(model, article.getViewConfig());
//                        return "modules/cms/front/themes/"+site.getTheme()+"/"+getTpl(article);
//                    }
//                }else if ("link".equals(category.getModule())){
//                    Page<Link> page = new Page<Link>(1, -1);
//                    page = linkService.findPage(page, new Link(category), false);
//                    model.addAttribute("page", page);
//                }
//                String view = "/frontList";
//                if (StringUtils.isNotBlank(category.getCustomListView())){
//                    view = "/"+category.getCustomListView();
//                }
//                CmsUtils.addViewConfigAttribute(model, category);
//                site =siteService.get(category.getSite().getId());
//                //System.out.println("else 栏目第一条内容 _2 :"+category.getSite().getTheme()+","+site.getTheme());
//                return "modules/cms/front/themes/"+siteService.get(category.getSite().getId()).getTheme()+view;
//                //return "modules/cms/front/themes/"+category.getSite().getTheme()+view;
//            }
//            // 有子栏目：显示子栏目列表
//            else{
//                model.addAttribute("category", category);
//                model.addAttribute("categoryList", categoryList);
//                String view = "/frontListCategory";
//                if (StringUtils.isNotBlank(category.getCustomListView())){
//                    view = "/"+category.getCustomListView();
//                }
//                CmsUtils.addViewConfigAttribute(model, category);
//                return "modules/cms/front/themes/"+site.getTheme()+view;
//            }
//        }
//    }
//    @RequestMapping("view-{categoryId}-{articaleId}${urlSuffix}")
//    public String view(Model model,@PathVariable String categoryId,@PathVariable String articaleId){
//        //获取左侧栏目列表
//        Category category = categoryService.get(categoryId);
//        if (category == null||!category.getModule().equals("article")) {
//            return "error/404";
//        }
//        model.addAttribute("site", category.getSite());
//        List<Category> categorieList = new ArrayList<Category>();
//        if ("1".equals(category.getParentId())) {
//            categorieList.add(category);
//        } else {
//            categorieList = CmsUtils.getCategoryList(category.getSite().getId(), category.getParentId(), 10, null);
//        }
//        model.addAttribute("categorieList", categorieList);
//        //获取文章
//        Article article = CmsUtils.getArticle(articaleId);
//        if (article == null||!article.getDelFlag().equals(Article.DEL_FLAG_NORMAL)) {
//            return "error/404";
//        }
//        model.addAttribute("article", article);
//        articleService.updateHitsAddOne(articaleId);//点击数加1
//        //获取相关文章
//        List<Object[]> relationList = articleService.findByIds(articleDataService.get(article.getId()).getRelation());
//        model.addAttribute("relationList", relationList);
//        //添加栏目和文章定义的自定义视图参数
//        CmsUtils.addViewConfigAttribute(model,article.getCategory());
//        CmsUtils.addViewConfigAttribute(model,article.getViewConfig());
//        Site site = CmsUtils.getSite(category.getSite().getId());
//        model.addAttribute("site", site);
//        return "modules/cms/front/themes/"+site.getTheme()+"/"+getTpl(article);
//    }
//
//    @RequestMapping(value = "comment",method = RequestMethod.GET)
//    public String comment(Model model,Comment comment, String theme, HttpServletRequest request, HttpServletResponse response){
//        Page<Comment> page = new Page<Comment>(request, response);
//        Comment c= new Comment();
//        c.setCategory(comment.getCategory());
//        c.setContentId(comment.getContentId());
//        c.setDelFlag(Comment.DEL_FLAG_NORMAL);
//        page = commentService.findPage(page, comment);
//        model.addAttribute("page", page);
//        model.addAttribute("comment", comment);
//
//        return "modules/cms/front/themes/"+theme+"/frontComment";
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "comment", method = RequestMethod.POST)
//    public String comment(Comment comment,String valiCode,@RequestParam(required = false) String replyId,HttpServletRequest request) {
//        if (StringUtils.isBlank(valiCode)) {
//            return "{result:2, message:'验证码不能为空。'}";
//        } else {
//            if (ValidateCodeServlet.validate(request, valiCode)) {
//                if (StringUtils.isNotBlank(replyId)) {
//                    Comment replyComment = commentService.get(replyId);
//                    if (replyComment != null) {
//                        comment.setContent("<div class=\"reply\">"+replyComment.getName()+":<br/>"
//                                +replyComment.getContent()+"</div>"+comment.getContent());
//                    }
//                    comment.setIp(request.getRemoteAddr());
//                    comment.setCreateDate(new Date());
//                    comment.setDelFlag(Comment.DEL_FLAG_AUDIT);
//                    commentService.save(comment);
//                    return "{result:1, message:'提交成功。'}";
//                }
//            } else {
//                return "{result:2, message:'验证码不正确。'}";
//            }
//        }
//        return "";
//    }
//
//
//    private String getTpl(Article article){
//        if(StringUtils.isBlank(article.getCustomContentView())){
//            String view = null;
//            Category c = article.getCategory();
//            boolean goon = true;
//            do{
//                if(StringUtils.isNotBlank(c.getCustomContentView())){
//                    view = c.getCustomContentView();
//                    goon = false;
//                }else if(c.getParent() == null || c.getParent().isRoot()){
//                    goon = false;
//                }else{
//                    c = c.getParent();
//                }
//            }while(goon);
//            return StringUtils.isBlank(view) ? Article.DEFAULT_TEMPLATE : view;
//        }else{
//            return article.getCustomContentView();
//        }
//    }
//}
