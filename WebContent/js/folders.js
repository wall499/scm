///////////////////////////////////////////////////////////////// INIT ////////////////////////////////////////////////////////////

//  Init!

var OutlookBar={
"format":
   {"heightpanel":25, "imagewidth":50, "imageheight":50, "arrowheight":17,"heightcell":100,"coloroutlook":"#DBEAF5","arrange_text":"bottom", "rollback":false, "img_arrows_up":"../images/pic/arup2.gif","img_arrows_dn":"../images/pic/ardn2.gif"},
"panels":
[
   {"text":"仓库管理", "panelCSS":"panel" ,"textCSS":"textpanel",
       "items":
       [
          {"text":"产品类别","textCSS":"a1", "image":'../images/pic/1.gif',    "url":'/200429SCM_BS/servlet/CategoryServlet', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"入库登记","textCSS":"a1","image":'../images/pic/2.gif',    "url":'/200429SCM_BS/servlet/StockInServlet', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"出库登记","textCSS":"a1", "image":'../images/pic/3.gif',    "url":'/200429SCM_BS/servlet/StockOutServlet', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"库存查询","textCSS":"a1", "image":'../images/pic/4.gif',    "url":'/200429SCM_BS/servlet/StockServlet', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"月度入库报表","textCSS":"a1", "image":'../images/pic/5.gif',    "url":'/200429SCM_BS/servlet/StockInSearchServlet', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"月度出库报表","textCSS":"a1", "image":'../images/pic/6.gif',    "url":'/200429SCM_BS/servlet/StockOutSearchServlet', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"产品库存报表","textCSS":"a1", "image":'../images/pic/7.gif',    "url":'/200429SCM_BS/servlet/StockReportServlet', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"产品管理","textCSS":"a1", "image":'../images/pic/17.gif',    "url":'/200429SCM_BS/servlet/ProductServlet', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"}
      ]
   },
    {"text":"采购管理", "panelCSS":"panel" ,"textCSS":"textpanel",
       "items":
       [
          {"text":"供应商管理","textCSS":"a1", "image":'../images/pic/1.gif',    "url":'gztm/th.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"添加采购单","textCSS":"a1","image":'../images/pic/2.gif',    "url":'gztm/fh.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"采购单了结","textCSS":"a1", "image":'../images/pic/3.gif',    "url":'gztm/yc.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"采购单查询","textCSS":"a1", "image":'../images/pic/4.gif',    "url":'gztm/js.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"付款登记","textCSS":"a1", "image":'../images/pic/5.gif',    "url":'gztm/tuihuocx.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"采购合同管理","textCSS":"a1", "image":'../images/pic/6.gif',    "url":'gztm/kccx.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"收支查询","textCSS":"a1", "image":'../images/pic/7.gif',    "url":'gztm/kcjjcx.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"月采购报表","textCSS":"a1", "image":'../images/pic/17.gif',    "url":'gztm/fy.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"}
      ]
   },
   {"text":"系统管理", "panelCSS":"panel" ,"textCSS":"textpanel",
       "items":
       [
          {"text":"用户管理","textCSS":"a1", "image":'../images/pic/8.gif',    "url":'xxwh/spxxsq.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"重新登录","textCSS":"a1", "image":'../images/pic/9.gif',    "url":'xxwh/khxx.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"退出系统","textCSS":"a1", "image":'../images/pic/10.gif',    "url":'xxwh/ccht.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"}
      ]
   },
   {"text":"销售管理", "panelCSS":"panel" ,"textCSS":"textpanel",
       "items":
       [
          {"text":"客户管理","textCSS":"a1", "image":'../images/pic/11.gif',    "url":'xxcx/spxx.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"新添销售单","textCSS":"a1", "image":'../images/pic/12.gif',    "url":'xxcx/splb.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"销售单了结","textCSS":"a1", "image":'../images/pic/13.gif',    "url":'xxcx/dlqy.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"销售单查询","textCSS":"a1", "image":'../images/pic/14.gif',    "url":'xxcx/ysbj.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"收款登记","textCSS":"a1", "image":'../images/pic/15.gif',    "url":'xxcx/ccbj.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"},
          {"text":"月销售报表","textCSS":"a1", "image":'../images/pic/16.gif',    "url":'xxcx/jbxx.htm', "classCSS":"imgstyle", "overclassCSS":"imgstyle_over"}
      ]
   }
]
};
