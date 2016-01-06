---
layout: post
title: "Create project page on GitHub"
description: ""
category: 
tags: []
---




#Project Page URL Structure
更新内容到GitHub上之前能在本地预览一下文章是一个很好排除基本错误的方法。
通过如下方式来设置url:

* 在_config.yml中，把baseurl设置成你的/project-name

* 在引用js/css时，使用`{{ site.baseurl }}/path/to/css.css`

* 使用permalinks or internal links时，使用`{{ site.baseurl }}{{ post.url }}`

* 在本地测试时，使用`localhost:4000/project-name`或者`jekyll serve --baseurl ''`

