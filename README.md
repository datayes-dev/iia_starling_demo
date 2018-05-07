# 萝卜投研

## 一.Layout优化

### (一).布局命名规范(Layout Name)

#### 1.Activity布局命名：
 应以"activity_"为前缀，每个单词间使用"_"进行连接

    例如: UserLoginActivity，则其布局命名应为"activity_user_login".
    
#### 2.Fragment布局命名：
 应以"fragment_"为前缀，每个单词间使用"_"进行连接

    例如: ResearchListFragment，则其布局命名应为"fragment_research_list".
    
#### 3.自定义View布局命名(这里是指使用Layout填充出来的View,即组合自定义View)：
 应以"view_"为前缀，每个单词之间使用"_"进行连接

    例如: CustomTitleBar，则其布局命名应为"view_costom_title_bar".
    
#### 4.Dialog布局命名(非系统AlertDialog,指自定义Dialog弹出层)：
 (1)应以"dialog_"为前缀，每个单词使用"_"进行连接
 (2)应以展示在Window中的位置为后缀，分别有"_top"、"_center"、"_bottom"

    例如：SwitchSlotGroupDialog，则其布局命名应为"dialog_switch_slot_group_top".
         SocialShareDialog，则其布局命名应为"dialog_social_share_bottom".
    
#### 5.PopupWindow布局命名：
 应以"popup_"为前缀，每个单词间使用"_"进行连接(PopupWindow在正常情况下，可以显示在任何位置，所以此处不以显示位置为后缀)

    例如：ReportFilterPopupWindow(研报筛选弹出框)，则其布局命名应为"popup_report_filter".
    
#### 6.列表Cell布局命名：(这里主要指ListView,GridView,RecyclerView的ViewHolder的布局)
 应以"item_"为前缀(不区分Cell的形状，如list还是grid)，每个单词间使用"_"进行连接
 
    例如：新闻列表无图Cell的ViewHolder是NonePictureNewsViewHolder,则其布局命名应为"item_none_picture_news".


### (二).布局复用(Layout Reuse)
参考文档：[Android最佳性能实践(四)——布局优化技巧](http://blog.csdn.net/guolin_blog/article/details/43376527)

#### 1.单独的控件在布局中使用，使用style的方式进行定制，暂不适用\<include>

#### 2.\<include>标签：

##### (1)介绍：
允许在一个布局中引入另外一个布局，页面布局中可能存在相同的布局，比如页面底部的确定按钮和取消按钮布局、Tab切换(TabLayout)等，
这个时候最好的做法就是将这个公共的部分提取到一个布局中，然后在这些页面中使用\<include>标签进行引入

##### (2)\<include>填充布局命名：
以"include_"为前缀，后面每个单词以"_"进行连接

    例如：标题栏复用布局命名应为"include_bottom_button_group".
    
##### (3)使用方式：

    <?xml version="1.0" encoding="utf-8"?>  
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        android:orientation="vertical" >  
      
        ......  

        <include layout="@layout/include_bottom_button_group" />  
      
    </LinearLayout> 


#### 3.\<merge>标签：

##### (1)介绍：
是作为\<include>标签的一种辅助扩展来使用的，它的主要作用是为了防止在引用布局文件时产生多余的布局嵌套。

##### (2)\<merge>填充布局命名：
以"merge_"为前缀，后面每个单词以"_"进行连接

    例如：merge复用布局的命名格式应为"merge_xxx_xxx".
    
##### (3)使用方式：

 merge布局：
 
    <?xml version="1.0" encoding="utf-8"?>  
    <merge xmlns:android="http://schemas.android.com/apk/res/android">  
      
        <Button  
            android:id="@+id/ok"  
            android:layout_width="match_parent"  
            android:layout_height="wrap_content"  
            android:layout_marginLeft="20dp"  
            android:layout_marginRight="20dp"  
            android:text="OK" />  
      
        <Button  
            android:id="@+id/cancel"  
            android:layout_width="match_parent"  
            android:layout_height="wrap_content"  
            android:layout_marginLeft="20dp"  
            android:layout_marginRight="20dp"  
            android:layout_marginTop="10dp"  
            android:text="Cancel" />  
      
    </merge>  
    
 布局中使用：
    
    <?xml version="1.0" encoding="utf-8"?>  
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        android:orientation="vertical" >  
          
        <EditText  
            android:id="@+id/edit"  
            android:layout_width="match_parent"  
            android:layout_height="wrap_content"  
            android:layout_marginBottom="10dp"  
            android:layout_marginLeft="20dp"  
            android:layout_marginRight="20dp"  
            android:layout_marginTop="10dp"  
            android:hint="Edit something here" />  
          
        <include layout="@layout/layout_merge_xxx_xxx"/>  
      
    </LinearLayout>  

#### 4.\<ViewStub>标签：

##### (1)介绍：
仅在需要时才加载布局。有的时候我们会遇到这样的场景，就是某个布局当中的元素非常多，但并不是所有元素都一起显示出来的，
而是普通情况下只显示部分常用的元素，而那些不常用的元素只有在用户进行特定操作的情况下才会显示出来。

##### (2)\<ViewStub>布局命名：
以"stub_"为前缀，后面每个单词以"_"进行连接
    
    例如：\<ViewStub>复用布局的命名格式应为"stub_xxx_xxx".
    
##### (3)使用方式：

###### Step 1:定义ViewStub的布局
    
    <?xml version="1.0" encoding="utf-8"?>  
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        android:orientation="vertical" >  
      
        <EditText  
            android:id="@+id/edit_extra1"  
            android:layout_width="match_parent"  
            android:layout_height="wrap_content"  
            android:layout_marginLeft="20dp"  
            android:layout_marginRight="20dp"  
            android:hint="Extra field 1" />  
      
        <EditText  
            android:id="@+id/edit_extra2"  
            android:layout_width="match_parent"  
            android:layout_height="wrap_content"  
            android:layout_marginLeft="20dp"  
            android:layout_marginRight="20dp"  
            android:layout_marginTop="10dp"  
            android:hint="Extra field 2" />  
      
        <EditText  
            android:id="@+id/edit_extra3"  
            android:layout_width="match_parent"  
            android:layout_height="wrap_content"  
            android:layout_marginLeft="20dp"  
            android:layout_marginRight="20dp"  
            android:layout_marginTop="10dp"  
            android:hint="Extra field 3" />  
      
    </LinearLayout> 
    
###### Step 2:在页面布局中进行使用

    <?xml version="1.0" encoding="utf-8"?>  
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        android:orientation="vertical" >  
      
        <EditText  
            android:id="@+id/edit"  
            android:layout_width="match_parent"  
            android:layout_height="wrap_content"  
            android:layout_marginBottom="10dp"  
            android:layout_marginLeft="20dp"  
            android:layout_marginRight="20dp"  
            android:layout_marginTop="10dp"  
            android:hint="@string/edit_something_here" />  
      
        <Button  
            android:id="@+id/more"  
            android:layout_width="wrap_content"  
            android:layout_height="wrap_content"  
            android:layout_gravity="right"  
            android:layout_marginRight="20dp"  
            android:layout_marginBottom="10dp"  
            android:text="More" />  
          
        <ViewStub   
            android:id="@+id/view_stub"  
            android:layout="@layout/<font color=#FF0000>layout_view_stub_xxx_xxx</font>"  
            android:layout_width="match_parent"  
            android:layout_height="wrap_content"  
            />  
      
        <include layout="@layout/ok_cancel_layout" />  
      
    </LinearLayout>     
     
###### Step 3:代码中动态加载布局

    public void onMoreClick() {  
        ViewStub viewStub = (ViewStub) findViewById(R.id.view_stub);  
        if (viewStub != null) {  
            View inflatedView = viewStub.inflate();  
            editExtra1 = (EditText) inflatedView.findViewById(R.id.edit_extra1);  
            editExtra2 = (EditText) inflatedView.findViewById(R.id.edit_extra2);  
            editExtra3 = (EditText) inflatedView.findViewById(R.id.edit_extra3);  
        }  
    } 
    
##### (4)注意：
<font color=#FF0000>注意这里我对ViewStub的实例进行了一个非空判断，这是因为ViewStub在XML中定义的id只在一开始有效，一旦ViewStub中指定的布局加载之后，
这个id也就失败了，那么此时findViewById()得到的值也会是空。</font>


## 二.Style使用及命名规范

### (一).Style的应用范围

* 1.可以作为整个App的Theme
* 2.可以单独作为某个Activity的Theme
* 3.Dialog的style
* 4.可以单独作用在某一个控件(View)上面

### (二).Style在App中的使用

#### 1 作为App的Theme或作为单个Activity的Theme

##### (1).使用

* App的Theme：在清单文件的\<application>标签中，通过android:theme属性引入使用。通过这个方式定义的Style，那么这个Style中的每个属性将会应用在所有页面的所有控件上。
* Activity的Theme：清单文件中注册你需要使用的Activity，在该\<Activity>标签的android:theme中引入你的theme，该theme会应用在当前Activity的所有控件上面。

##### (2).命名规范

* App的Theme具有唯一性，命名方式：<font color=#00FFFF>应用名.Theme</font>
* Activity的Theme集成自App的Theme，命名方式：<font color=#00FFFF>应用名.Theme.Xxx.xxx</font>


#### 2 使用在Dialog上的Style

##### (1).使用

创建Dialog的时候，通过构造方法传递Dialog的Style

##### (2). 命名规范(以Dialog出现的方式定义Style)



#### 3 作用在某个控件(View)上的Style

##### (1).使用

页面布局文件中，在对应控件(View)使用android:style属性进行使用

##### (2).命名规范

* 使用大驼峰命名法



