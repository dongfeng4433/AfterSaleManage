
js.GetLinkButton = function (action, content, force) {
	if (!action) return $('a[action]', content);
	var buttons = js._buttons[action];
	if (force || !buttons) {
		buttons = $('a[action=' + action + ']', content);
		js._buttons[action] = buttons;
	}
	return buttons;
}

/************************************************** 
返回剪贴板的内容 
**************************************************/
js.Web.GetClipboard = function (targetElement, showpastearea, createDiv, isClick) {
	var isIE = js.Browser ? js.Browser.isIE : false;
	if (isIE) {
		return (window.clipboardData.getData('text'));
	} else {
		if (createDiv) {
			var pasetArea = document.createElement('div');
			$(pasetArea).css('display', 'none').css('position', 'absolute');
			$(pasetArea).attr('tname', $(targetElement).attr('name')).attr('gname', 'pastetext');
			$(pasetArea).append('<textarea name="pastearea" style="width:200px;height:100px"></textarea>');
			document.body.appendChild(pasetArea);
		} else if (!isClick) {//粘贴操作不使用flash,因为在谷歌浏览器下flash点击导致剪贴板数据清空
			var cb = js.Web.GetZeroClipboard(targetElement);
			if (cb) return cb.getText(showpastearea);
			return;
		}
		if (isClick) {
			if (!isIE) {
				var div = $('div[tname="' + $(targetElement).attr('name') + '"]');
				var x = ($(window).width() - 200) / 2, y = ($(window).height() - 100) / 2;
				$(div).css('display', 'block').css("top", y).css("left", x);
				$(div).find('textarea').val('');
				$(div).find('textarea').focus();
			} else {
				return (window.clipboardData.getData('text'));
			}
		}
	}
	return null;
}
js.Web.GetZeroClipboard = function (targetElement) {
	if (!js.Web._ZeroClipboard) js.Web._ZeroClipboard = {};
	var targetElName = $(targetElement).attr('name');
	if (!js.Web._ZeroClipboard[targetElName] && window.ZeroClipboard) {
		ZeroClipboard.setMoviePath(js.Web.GenerateAbsoluteUrl('/Scripts/jquery.clipboard.swf', false));
		var id = js.CreateNewId();
		var dom = null;
		if (targetElement) {
			dom = targetElement.target ? targetElement.target : targetElement;
		} else {
			dom = document.createElement('div');
			dom.id = id;
			document.body.appendChild(dom);
		}
		var pasetArea = document.createElement('div');
		$(pasetArea).css('display', 'none').css('position', 'absolute');
		$(pasetArea).attr('tname', targetElName).attr('gname', 'pastetext');
		$(pasetArea).append('<textarea name="pastearea" style="width:200px;height:100px"></textarea>');
		document.body.appendChild(pasetArea);
		js.Web._ZeroClipboard[targetElName] = new ZeroClipboard.Client(dom);
		js.Web._ZeroClipboard[targetElName].setHandCursor(true);
		js.Web._ZeroClipboard[targetElName].id = id;
		js.Web._ZeroClipboard[targetElName].el = dom;
		js.Web._ZeroClipboard[targetElName].pasetArea = pasetArea;
		js.Web._ZeroClipboard[targetElName].addEventListener('load', function (client) {
			//debugstr("准备就绪");
		});

		js.Web._ZeroClipboard[targetElName].addEventListener('mousedown', function (client) {
			//client.domElement.call('onclick', client.domElement);
			try {
				client.domElement.onclick.call(client.domElement);
			}
			catch (ex) {
				js.Web.ShowMsg('错误: ' + ex);
			}
		});

		js.Web._ZeroClipboard[targetElName].addEventListener('complete', function (client) {
			var actionType = $(client.domElement).attr('name');
			switch (actionType) {
				case 'copy':
				case 'copy2':
					if (js.Web.LastClipboardText) {
						js.Web.ShowMsg('已经复制成功!');
					} else {
						js.Web.ShowMsg('您没有复制任何数据!');
					}
					break;
				case 'paste':
				case 'paste2':
					//js.Web.ShowMsg("已经复制到粘贴板", '提示', null, null, 0);
					break;
				default:
					break;
			}

		});

	}
	return js.Web._ZeroClipboard[targetElName];
}

//清除或者初始化某一个元素的子元素的值
js.Web.ClearHtmlElements = function (selector, clearHidden) {
	var item = selector;
	if (clearHidden) item.find("input[type=hidden]").val("");
	item.find("input[type=text], textarea").val("");
	item.find("input[type=radio]").attr({ checked: false });
	item.find("input[type=checkbox]").attr({ checked: false });
	item.find("select option").attr('selected', '');
	item.find("select").attr('value', '');
	item.find("select").val('');
}
//用此函数自动获取一个列表， 并初始化对象
js.Web.GetHtmlElements = function (selector, elementDict) {
	var jselector = selector;
	if (!js.IsJQuery(selector)) jselector = $(selector);
	elementDict = elementDict || {};
	elementDict.Fields = [];//这个数组里存放一些能够输入的字段
	elementDict.Buttons = []; //这个数组里存放的是按钮
	items = jselector.find('input[type=checkbox]');
	var checkNames = {};
	items.each(function (index, item) {
		var titem = $(item);
		if (item.checked) {
			var name = js.Controls.GetName(titem); elementDict[name] = titem;
			elementDict.Fields.push(titem); checkNames[name] = titem;
		}
	});

	var items = jselector.find('input, textarea, select,radio');
	items.each(function (index, item) {
		var titem = $(item);
		var name = js.Controls.GetName(titem);
		if (checkNames[name] && titem.attr('type') == 'hidden') return;
		var labelField = null;//在这里找到LabelField
		js.Web.AttachCommonFieldFunctions(titem);
		elementDict[name] = titem;
		elementDict.Fields.push(titem);
	});

	return elementDict;
}
js.Web.RoundTableTrText = function (selector, precision) {
	var item = selector;
	item.find("td").each(function () { RoundTableTdText($(this), precision); });
}
js.Web.RoundTableTdText = function (selector, precision) {
	var item = selector;
	var value = selector.text().replace(/(^\s*)|(\s*$)/g, '').replace(' ', '');
	if (value != null && value != '' && !isNaN(value)) {
		var num = parseFloat(value);
		selector.text(num.toFixed(precision));
	}
}
//清除某个Html节点的子节点
js.Web.ClearChildren = function (element) {
	for (var i = element.childNodes.length - 1; i >= 0; i--)
		element.removeChild(element.childNodes[i]);
}
//设置一个table的innerHTML属性
js.Web.SetTableInnerHTML = function (table, innerHTML, isJustBody) {
	//table.innerHTML=innerHTML;
	//解决快捷键tbody
	var isIE = true;
	if (isIE) {
		var temp = window.document.createElement('div');
		if (isJustBody) temp.innerHTML = '<table><tbody>' + innerHTML + '</tbody></table>';
		else temp.innerHTML = '<table>' + innerHTML + '</table>';

		if (table.tBodies.length <= 0) {
			var tbody = window.document.createElement('tbody');
			table.appendChild(tbody);
		}
		if (isJustBody) table.replaceChild(temp.childNodes[0].tBodies[0], table.tBodies[0]);
		else {
			js.Web.ClearChildren(table);
			var newTable = temp.childNodes[0];
			for (var i = newTable.childNodes.length - 1; i >= 0; i--) table.appendChild(newTable.childNodes[i]);
		}
	}
	else {
		var has = false;
		for (var i = 0; i < table.childNodes.length; i++)
			if (table.childNodes[i].nodeName.toLower() == 'tbody') { table.childNodes[i].innerHTML = innerHTML; return; }
		table.innerHTML = innerHTML;
	}
}
js.Web.AttachCommonFieldFunctions = function (field) {
	if (js.IsString(field)) field = $(field);
	else if (!js.IsJQuery(field) && js.IsObject(field)) field = $(field);
	if (!js.IsJQuery(field)) return field;
	field.getName = null;
	field.getValue = null;
	field.setValue = null;
	field.setText = null;
	return field;
}

//初始化Ajax页面
js.Web.InitAjaxPage = function (data) {
	var srcs = data.match(/<script.*?src ?= ?"([^"]+)"/ig);
	if (srcs == null) return;
	var nsrcs = {}, index, nsrc;
	for (var i = 0; i < srcs.length; i++) {
		nsrc = srcs[i].match(/src ?= ?"([^"]+)"/i)[1];
		nsrcs[nsrc] = nsrc;
	}
	for (var src in nsrcs) {
		$.getScript(src, function (data, textStatus, xhr) {
		});
	}
}
//序列化一个form里所有的元素, 形成一个对象
js.Web.SerializeForm = function (form) {
	if (!form) return '';
	var formElements = form.elements, params = {};
	//if (formElements && Sys.Mvc && Sys.Mvc.MvcHelpers && Sys.Mvc.MvcHelpers._serializeForm) return Sys.Mvc.MvcHelpers._serializeForm(form);
	if (!formElements) {
		formElements = [];
		$('input', form).each(function (index, item) { formElements.push(item); });
		$('SELECT', form).each(function (index, item) { formElements.push(item); });
		$('TEXTAREA', form).each(function (index, item) { formElements.push(item); });
	}
	var count = formElements.length;
	function addNameValue(name, value) {
		var tvalue = params[name];
		if (tvalue === undefined) params[name] = value;
		else if (js.IsArray(tvalue)) tvalue.push(value);
		else params[name] = [tvalue, value];
	}
	for (var i = 0; i < count; i++) {
		var element = formElements[i];
		var name = element.name;
		if (!name || !name.length || element.disabled) continue;
		var tagName = element.tagName.toUpperCase();
		if (tagName === 'INPUT') {
			var inputElement = element;
			var type = inputElement.type;
			if ((type === 'text') || (type === 'password') || (type === 'hidden') || (((type === 'checkbox') || (type === 'radio')) && element.checked)) {
				addNameValue(name, inputElement.value);
			}
		}
		else if (tagName === 'SELECT') {
			var selectElement = element;
			var optionCount = selectElement.options.length;
			for (var j = 0; j < optionCount; j++) {
				var optionElement = selectElement.options[j];
				if (optionElement.selected) {
					addNameValue(name, optionElement.value);
				}
			}
		}
		else if (tagName === 'TEXTAREA') {
			addNameValue(name, element.value);
		}
	}
	var additionalInput = form._additionalInput;
	if (additionalInput) {
		return EncodeUrl(params, null, null, null, null, true) + '&' + additionalInput;
	}
	return params;
}
/**
* 执行一个ajax请求， showDefaultMsg表示是否显示默认的消息进度条, hideResponseMsg表示是否隐藏服务器返回的消息
* @callback 方法原型为function(success, data, params)
**/
js.Web.AjaxRequest = function (sender, url, params, callback, success, failure, isPost, showDefaultMsg, hideResponseMsg, timeout) {
	if (!url || url == '#') { //判断url是否为空，如果debug模式则弹出提示，否则不执行
		return js.Sys.ShowDebugAlert('url为空，请检查你的代码是否正确', true);
	}
	if (js.IsNull(showDefaultMsg) || showDefaultMsg === true) showDefaultMsg = '操作执行中，请等待...';
	params = params || {};
	var progress = null, addRandom = params._addRandom;
	delete params._addRandom;
	if (showDefaultMsg && $.messager)
		$.messager.progress({ title: showDefaultMsg, interval: 1000 });
	else
		if (showDefaultMsg && window.Ext) { progress = Ext.MessageBox.progress("提示", showDefaultMsg, ""); }
	try {
		var senderControl = null;
		if (sender && !sender.el && !sender.render)
			try {
				senderControl = $(sender);
			} //!sender.el和!sender.render代表不是自定义的对象，比如extjs的
			catch (ex) {
				senderControl = null;
			}
		if (senderControl && senderControl.hide) senderControl.hide();
		var type = 'GET';
		if (isPost == true) type = 'POST'; else if (isPost) type = isPost;
		js.Init.RemoveLocalProperties(params, true);
		if (typeof (params) == 'object') params = js.Web.AppendAjaxHeader(params, url, addRandom);
		else url = js.Web.AppendUrlParams(url, js.Web.AppendAjaxHeader(null, url, addRandom));
		function doAction(data, rsuccess) {
			if (data.url) {
				js.Web.OpenWindow(sender, data.url, data.target || '_self', null, null, null);
				return true;
			}
			else
				if (rsuccess && callback && typeof (callback) == 'string')
					window.open(callback, '_self'); //如果callback是string, 则代表是一个url
		}
		if (timeout && timeout < 30) timeout = timeout * 1000;
		jQuery.ajax({
			url: url, context: sender || this, success: function (data) {
				if (showDefaultMsg && $.messager) $.messager.progress('close');
				else if (showDefaultMsg && progress) progress.hide();
				try {
					if (typeof (data) == 'string') {
						try { data = eval('(' + data + ')'); } catch (ex) { }
					}
					var rsuccess = data && data.success;
					if (rsuccess && success) success(sender, data, params);
					else if (!rsuccess && failure) failure(sender, data, params);
					if (callback && typeof (callback) == 'function') callback(rsuccess, data, params);
					if (data && data.msg && !hideResponseMsg) js.Web.ShowMsg(data.msg, '提示', null, function () {
						doAction(data, rsuccess);
					}, 0);
					else if (showDefaultMsg && !hideResponseMsg) js.Web.ShowMsg(rsuccess ? js.UI.UIText('Info_Action_Success') : js.UI.UIText('Info_Action_Failure'), null, null, function () {
						doAction(data, rsuccess);
					}, 0);
				}
				catch (e) { js.Web.ShowAlertMsg(e); }
				finally {
					if (senderControl != null) senderControl.show();
				}
			}, failure: function (data) {
				if (showDefaultMsg && $.messager) $.messager.progress('close');
				else if (showDefaultMsg && progress) progress.hide();
				try {
					if (failure) failure(sender, data, params);
					if (callback) callback(false, data, params);
					if (data && data.msg && !hideResponseMsg) js.Web.ShowMsg(data.msg, '提示', null, function () {
						doAction(data, false);
					}, 0);
					else if (showDefaultMsg && !hideResponseMsg) js.Web.ShowMsg(js.UI.UIText('Info_Action_Failure'), '失败', null, function () {
						doAction(data, false);
					}, 0);
				}
				catch (e) { js.Web.ShowAlertMsg(e); }
				finally {
					if (senderControl != null) senderControl.show();
				}
			}, error: function (handle, type, error) {
				try {
					if (showDefaultMsg && $.messager) $.messager.progress('close');
					else if (showDefaultMsg && progress) progress.hide();
					error = js.Web.GetFriendlyErrorMessage(error, type);
					if (!hideResponseMsg) js.Web.ShowMsg('不好意思失败了, 附加消息为: ' + error);
					if (callback && typeof (callback) == 'function') callback(false, { success: false, msg: error }, params);
				}
				catch (e) { js.Web.ShowAlertMsg(e); }
				finally {
					if (senderControl != null) senderControl.show();
				}
			}, data: params, type: type, timeout: timeout
		});
	} catch (ex) {
		if (showDefaultMsg && $.messager) $.messager.progress('close');
		js.Web.ShowMsg('发生异常错误，错误信息: ' + ex);
	}
}
js.Web.AjaxLinkClicked = function (sender, url, params, isFormSubmit, callback, confirmText, event) {
	var jsender = $(sender), href = jsender.attr('href');
	url = js.Web.IsBlankLink(sender, href) ? url : href;
	sender = sender || this;
	if (confirmText && typeof (confirmText) == 'function' && confirmText(sender, url, params, isFormSubmit, callback) === false) return;
	if (confirmText && typeof (confirmText) == 'string') {
		js.Web.ShowMsg(confirmText, '提示', null, function (r) {
			if (r) doAction();
		}, 2);
		return false;
	}
	function doAction() {
		var form = null;
		var ourl = null;
		try {
			if (isFormSubmit) {
				form = js.Web.FindForm(sender);
				if (!form) return;
				ourl = form.action;
				url = url || ourl;
				js.Web.SubmitForm(form, url, params, callback, null, true);
				return false;
			}
			js.Web.AjaxRequest(sender, url, params, callback, null, null, isFormSubmit, true);
		}
		finally {
			if (form && ourl) form.action = ourl;
		}
		js.StopEvent(event || window.event);
		return false;
	}
	return doAction();
}
//一个连接的点击事件
js.Web.LinkClicked = function (sender, url, isSubmit, isReset, isTopWindow, confirmText, callback, params, isAjaxMethod, event) {
	event = event || window.event;
	if (isReset) { var form = js.Web.FindForm(sender); if (form) { form.reset(); js.Web.ClearHtmlElements($(form), true); } return false; }
	if (isAjaxMethod) return js.Web.AjaxLinkClicked(sender, url, params, isSubmit, callback, confirmText);
	var jsender = $(sender), href = jsender.attr('href');
	url = js.Web.IsBlankLink(sender, href) ? url : href;
	sender = sender || this;
	if (confirmText && typeof (confirmText) == 'function' && confirmText(sender, url, params, isSubmit, callback) === false) return false;
	else if (confirmText && typeof (confirmText) == 'string') {
		js.Web.ShowMsg(confirmText, '提示', null, function (r) {
			if (r) doAction();
		}, 2);
		return false;
	}

	function doAction() {
		if (confirmText === false) return false;
		if (confirmText && !confirmText(sender, isSubmit, isReset, isTopWindow)) return false;
		if (!isSubmit) {
			var topWindow = isTopWindow ? js.Web.FindWindow(window, null) : window;
			if (!url) return true;
			if (params) url = js.Web.AppendUrlParams(url, params);
			topWindow.open(url, sender.target || '_self', null); return false;
		}
		var form = js.Web.FindForm(sender); if (!form) return false;
		var ourl = form.action;
		try {
			url = js.Web._ChangePostUrl(url || ourl);
			js.Web.SubmitForm(form, url, params, callback, null, true);
		}
		finally { form.action = ourl; }
		return false;
	}
	return doAction();
}

//注册一个ViewPage
js.Web.RegisterViewPage = function (pageClassName, configs) {
	if (window.CollectGarbage) CollectGarbage();
	$(function () {
		try {
			window.ViewPage = new pageClassName(configs);
			if (js.Controls && js.Controls.ComponentContainer) {
				for (var id in js.Controls.ComponentContainer) {
					var con = js.Controls.ComponentContainer[id];
					if (con.loadFirstTime && con.methods && con.methods.read)
						con.methods.read();
				}
			}
		}
		catch (ex) {
			js.Web.ShowAlertMsg('加载页面失败，附加信息: ' + ex.message + ', 堆栈: ' + ex.stack);
		}
	});
}
//使用特殊的方式进行提交数据, form是一个html node, url是一个新的url或者可以为null
js.Web.SubmitForm = function (form, url, params, callback, options, showDefaultMsg, confirmText, ingoreValiduate) {
	var jform = form;
	if (!js.IsJQuery(form)) jform = $(form);
	form = jform[0];
	options = options || {};
	if (!ingoreValiduate && form.onValiduate && !form.onValiduate())
		return false;
	if (!ingoreValiduate && !js.Controls.ValidateBox(jform)) {
		return false;
	}
	function doAction() {
		if ($.messager) $.messager.progress({ title: '操作执行中，请等待...', interval: 1000 });
		try {
			var id = form.target || js.GenerateUnquieId(),
				oframe = document.getElementById(id),
				frame = document.createElement('iframe'),
				jframe = $(frame),
				hiddens = [],
				encoding = 'multipart/form-data',
				buf = { target: form.target, method: form.method, encoding: form.encoding, enctype: form.enctype, action: form.action },
				addField = function (name, value) {
					hiddenItem = document.createElement('input');
					var jhiddenItem = $(hiddenItem).attr({ type: 'hidden', value: value, name: name });
					form.appendChild(hiddenItem);
					hiddens.push(hiddenItem);
				},
				hiddenItem, obj, value, name, vLen, v, hLen, h;
			if (oframe) { frame = oframe; jframe = $(oframe); jframe.addClass('eui-invisbile'); }
			else
			{
				jframe.attr({ id: id, name: id, src: js.SSL_SECURE_URL });
				jframe.addClass('eui-invisbile');
				document.body.appendChild(frame);
				if (document.frames) { document.frames[id].name = id; }
			}
			var nUrl = url || buf.action;
			if (!nUrl || nUrl == '#') { //判断url是否为空，如果debug模式则弹出提示，否则不执行
				return js.Sys.ShowDebugAlert('url为空，请检查你的代码是否正确', true);
			}
			params = js.Apply({ rtype: 'text', 'X-Requested-With': 'XMLHttpRequest' }, params);
			nUrl = js.Web.AppendUrlParams(nUrl, params, false);
			jform.attr({ target: id, method: 'POST', enctype: encoding, encoding: encoding, action: nUrl });
			form.setAttribute('target', id);

			try {
				jframe.on('load', function (event) {
					if ($.messager) $.messager.progress('close');
					var me = this, responseText, response, success, doc, contentNode, tcallback;
					try {
						doc = frame.contentWindow.document || frame.contentDocument || window.frames[frame.id].document;
						if (doc) {
							//if (Ext.isOpera && doc.location == 'about:blank') {return;}
							if (doc.body) {
								if ((contentNode = doc.body.firstChild) && /pre/i.test(contentNode.tagName)) { responseText = contentNode.textContent || contentNode.innerText; }
								else if ((contentNode = doc.getElementsByTagName('textarea')[0])) { responseText = contentNode.value; }
								else { responseText = doc.body.textContent || doc.body.innerText; }
							}
							tcallback = options.success;
							success = true;
						}
					} catch (e) {
						responseText = '{success:false,msg:"' + (e.message || e.description).trim() + '"}';
						tcallback = options.failure;
						success = false;
					}
					try { response = eval('(' + responseText + ')'); } catch (ex) { success = false; response = { success: false, msg: responseText }; }
					if (!response) response = {};
					if (!js.IsNull(response.success)) success = response.success;
					if (tcallback) tcallback(success, response);
					if (callback && typeof (callback) == 'function') callback(success, response);
					if (response.msg) { js.Web.ShowMsg(response.msg, '提示', null, doAction, 0); }
					else if (showDefaultMsg) { js.Web.ShowMsg(success ? js.UI.UIText('Info_Action_Success') : js.UI.UIText('Info_Action_Failure'), '提示', null, doAction, 0); }

					function doAction() {
						if (response.url) { js.Web.OpenWindow(this, response.url, response.target || '_self'); return true; }
						else if (success && callback && typeof (callback) == 'string') js.Web.OpenWindow(this, callback, '_self', null, null, null); //如果callback是string, 则代表是一个url
						window.setTimeout(function () { jframe.remove(); }, 100);
					}
				});
				form.submit();
			}
			catch (e) {
				if (callback) callback(false, { success: false, msg: e });
			}
			finally {
				jform.attr(buf);
				hLen = hiddens.length;
				for (h = 0; h < hLen; h++) { $(hiddens[h]).remove(); }
			}
		} catch (ex) {
			if ($.messager) $.messager.progress('close');
			js.Web.ShowMsg('发生异常错误，错误信息: ' + ex);
		}
	}

	if (confirmText) {
		js.Web.ShowMsg(confirmText, "提示", null, function (success) {
			if (success) doAction();
		}, 2);
		return;
	}
	return doAction();
}
//打印一块区域，使用html套打的方式
js.Web.PrintPanel = function (printDirectly, panelOrValue, name, configs, windowConfigs) {
	if (printDirectly) { window.focus(); window.print(); return; }
	if (!configs) configs = {};
	if (!windowConfigs) windowConfigs = {};
	//var nowdate = new Date();		
	//var windowName = new Date().format('yyyyMMddHHmmss');
	var windowName = js.GenerateUnquieId();
	function printTemplate(content, panelOrValue, configs) {
		if (!panelOrValue) panelOrValue = {};
		var s = content;
		var outPrintWindow = window.open('#', windowName, null, null);
		if (configs.title) { panelOrValue['Page_Title'] = configs.title; }
		js.CopyObject(configs, panelOrValue, true);
		var tValue; var tname;
		for (var a in panelOrValue) {
			tValue = panelOrValue[a];
			if (typeof tValue == 'function') tValue = tValue();
			tname = '{' + a + '}';
			while (s.match(tname)) s = s.replace(tname, tValue);
		}
		try {
			$(outPrintWindow.document).html(s);
		}
		catch (ex) { }
		if (!confirm('已经在另外的窗口中准备好打印页面，请点击"确定"确认转到该页面，点击"取消"继续操作!')) { return; }
		outPrintWindow.focus();
		outPrintWindow.print();
	}
	if (name == null) {
		var s = document.documentElement.outerHTML;
		var bodyContent = (document.getElementById('main') || document.body).innerHTML;
		var panelDiv = document.getElementById(panelOrValue);
		if (panelDiv) panelOrValue = panelDiv.innerHTML;
		s = s.replace(bodyContent, panelOrValue);
		var footerContent = document.getElementById('footer');
		if (footerContent) {
			footerContent = footerContent.outerHTML;
			s = s.replace(footerContent, '');
		}
		printTemplate(s, null, configs);
	}
	else {
		var outUrl = js.Web.GenerateUrl('Home', 'GetPrintTemplate', name, { TemplateId: name });
		$.ajax(outUrl).done(function (data) { printTemplate(data, panelOrValue, configs); }).fail(function () { js.Web.ShowMsg('获取打印模板失败!', '失败', null, null, 0); });
	}
}

js.Init.RestoreJQueryFunctions = function () {
	js.Init.IsJQueryFunctionStored = true;
	for (var name in js.Init._JQueryFunctions)
		jQuery.fn['jQuery_' + name] = js.Init._JQueryFunctions[name];
}

//初始化编辑字段, selector可以为空
js.Init.InitEditFields = function (isInitPage, selector) {
	if (!selector && !js.Sys.InitEditFields) return;
	var tables = null;
	if (selector && js.IsJQuery(selector))
		tables = selector.find(' .eui-table');
	else tables = $((selector || '') + ' .eui-table');
	function calcuateTableCells(field, parentNode) {
		field = field || this;
		var table = $(field),
			tableDataOptions = table.data('dataOptions') || table.attr('data-options') || '_end:null',
			jparentNode = $(parentNode),
			pdialog = table.parents('.eui-dialog'),
			pWidth = jparentNode.innerWidth(),
			pHeight = jparentNode.innerHeight(),
			cols = table.find('thead th.eui-table-head-cell'),
			rows = table.find('tbody tr');

		if (tableDataOptions && js.IsString(tableDataOptions)) {
			tableDataOptions = eval('({' + tableDataOptions + '})');
			table.data('dataOptions', tableDataOptions);
		}

		//var tableWidth = table.width(), colWidths=[],
		var tableWidth = table.width(), colWidths = [], colOptionArray = [],
			sumPercent = 0, sumPixel = 0, totalPercentWidth = 0,
			dataOptions = null, totalSumPercent = 1;
		if (tableWidth > pWidth && pWidth > 60) tableWidth = pWidth;
		//如果本table外面有一个dialog，那么需要宽度减去20
		if (pdialog.length > 0) tableWidth -= 20;
		//table.width(pWidth);

		js.Each(cols, function (field, index) {
			var tthis = $(field), tdataOptions = tthis.data('dataOptions') || tthis.attr('data-options') || '_end:null';
			if (tdataOptions && js.IsString(tdataOptions)) {
				var dataOptions = eval('({' + tdataOptions + '})');
				tthis.data('dataOptions', dataOptions);
			} else dataOptions = tdataOptions;
			if (dataOptions.width > 0 && dataOptions.isPercent) sumPercent += dataOptions.width;
			else if (dataOptions.width > 0 && dataOptions.isPercent == false) sumPixel += dataOptions.width;
		});
		totalPercentWidth = tableWidth - sumPixel - 2;
		if (totalPercentWidth < 0 && sumPixel > 0) {
			totalPercentWidth = 0;
			totalSumPercent = tableWidth / sumPixel;
		}
		js.Each(cols, function (field, index) {
			var tthis = $(field);
			dataOptions = tthis.data('dataOptions');
			if (!dataOptions) return;
			if (dataOptions.width > 0) {
				if (totalPercentWidth > 0 && dataOptions.isPercent) dataOptions.realWidth = totalPercentWidth * dataOptions.width / sumPercent;
				else if (dataOptions.isPercent == false) dataOptions.realWidth = dataOptions.width * totalSumPercent;
				else dataOptions.realWidth = 0;
			} else dataOptions.realWidth = 0;
			if (!dataOptions.DefaultLabelWidth) dataOptions.DefaultLabelWidth = tableDataOptions.DefaultLabelWidth;
			if (!dataOptions.PaddingWidth) dataOptions.PaddingWidth = tableDataOptions.PaddingWidth;
			dataOptions.realWidth -= 2;
			tthis.width(dataOptions.realWidth);
			colWidths.push(dataOptions.realWidth);
			colOptionArray.push(dataOptions);
		});
		js.Each(rows, function (trow, index) {
			var row = $(trow), cells = row.find('td'), colIndex = 0;
			js.Each(cells, function (field, index) {
				var tthis = $(field), tdataOptions = tthis.data('dataOptions') || tthis.attr('data-options') || '_end:null', comCell = tthis.find('.eui-cell-combine'),
					colSpan = Number(tthis.attr('colSpan')), rowSpan = Number(tthis.attr('rowSpan')), cellWidth = 0;//不支持rowSpan
				if (tdataOptions && js.IsString(tdataOptions)) {
					dataOptions = eval('({' + (tdataOptions || '') + '})');
					dataOptions.colSpan = Number(colSpan || 1); dataOptions.rowSpan = Number(rowSpan || 1);
					dataOptions.colIndex = Number(colIndex);
					dataOptions.DefaultLabelWidth = dataOptions.DefaultLabelWidth || colOptionArray[colIndex].DefaultLabelWidth || tableDataOptions.DefaultLabelWidth || 90;
					dataOptions.PaddingWidth = dataOptions.PaddingWidth || colOptionArray[colIndex].PaddingWidth || tableDataOptions.PaddingWidth || 0;
					tthis.data('dataOptions', dataOptions);
				}
				else dataOptions = tdataOptions;
				for (var ci = 0; ci < dataOptions.colSpan; ci++)
					cellWidth += colWidths[ci + colIndex];
				colIndex += Number(dataOptions.colSpan); dataOptions.realWidth = Number(cellWidth);

				cellWidth = dataOptions.realWidth;
				tthis.width(cellWidth);
				//cellWidth = tthis.innerWidth();
				if (comCell.length > 0) {
					var labelSpan = comCell.find('.eui-table-cell-label'), labelWidth = labelSpan.width(), alabelWidth = labelSpan.attr('width');
					if (labelWidth < 50 || !alabelWidth) labelWidth = dataOptions.DefaultLabelWidth;
					labelSpan.width(labelWidth);
					var fieldSpan = comCell.find('.eui-table-cell-field'),
						width = cellWidth - labelWidth - 25 - dataOptions.PaddingWidth;
					if (width <= 0) width = cellWidth;
					if (fieldSpan.length > 0) {
						fieldSpan.width(width);
						var firstVisibleChild = null, child = null, firstChild = null, hasTip = false;
						for (var i = 0; i < fieldSpan[0].children.length; i++) {
							child = fieldSpan[0].children[i];
							if (!firstChild) firstChild = $(child);
							if ($(child).width() > 5 && $(child).is(':visible')) { firstVisibleChild = $(child); break; }
							//if ($(child).width() > 5) { firstVisibleChild = $(child); break; }
						}
						hasTip = fieldSpan.find('.eui-tooltip');
						if (hasTip) width -= 20;
						if (firstChild) {
							var combo = firstChild.data('combobox');
							if (combo) { firstChild.combobox('resize', width - 2); }
							else if (firstVisibleChild) firstVisibleChild.width(width - 7);
						}
					}
				}
			});
		});
	}
	tables.each(function (index, field) {
		calcuateTableCells(field, field.parentNode);
		//field.parentNode.onresize = function () {
		//	var pNode = this, field = pNode.getElementsByTagName('table')[0];
		//	calcuateTableCells(field, pNode);
		//}
	});

	//js.Init.ShowRenderTimeCost();
	//var fields = $('.table-edit table.eui-table div.eui-cell-combine');
	//js.Each(fields, function (field, index) {
	//	var parent = $(field), parentWidth = parent.width(),
	//		labelSpan = parent.find('.eui-table-cell-label'), labelWidth = labelSpan.width(),
	//		fieldSpan = parent.find('.eui-table-cell-field'),
	//		width = parentWidth - labelWidth - 20;
	//	if (fieldSpan.length<=0) return;
	//	fieldSpan.width(width);
	//	var firstVisibleChild = null, child = null;
	//	for (var i = 0; i < fieldSpan[0].children.length; i++) {
	//		child = fieldSpan[0].children[i];
	//		if ($(child).width() > 5){ firstVisibleChild = $(child);break;}
	//	}
	//	if (firstVisibleChild) firstVisibleChild.width(width - 5);
	//}, null, false, false);
}
js.Init.InitPreInitControls = function () {
	var controls = js.PreInitControls;
	function findControls(elements, container, allContainer, func) {
		var name;
		for (var i = 0; i < elements.length; i++) {
			name = elements[i].name || elements[i].id;
			if (!name) { name = js.CreateNewId(); elements[i].id = name; }
			elements[i].name = name;
			container[name] = elements[i];
			if (func) { func(elements[i], name, container); }
			allContainer.push(elements[i]);
		}
	}
	findControls($('a'), controls.Buttons, controls.Buttons._All);
	findControls($('form'), controls.Forms, controls.Forms._All, function (ele, name, container) {
		controls.TextBoxs[name] = {};
		findControls($(' input[type=text]', ele), controls.TextBoxs[name], controls.TextBoxs._All);
	});
	if (js.Controls && js.Controls._initButtonMethods) js.Controls._initButtonMethods();

	var dInputs = $('input[data-options]');
	for (var i = 0; i < dInputs.length; i++) {
		var dInput = $(dInputs[i]);
		var odata = eval('({' + dInput.attr('data-options') + '})');
		dInput.data('data-options', odata);
		dInput.keydown(js.Keys.GeneralKeyDown);
	}
}
//呈现tabs
js.Init.RenderTabs = function () {
	var target = $('.eui-tabs');
	var configs = {
		ajaxOptions: {
			error: function (xhr, status, index, anchor) {
				$(anchor.hash).html("不能正常导入Tab，请与管理者联系！");
			}
		},
		onSelect: {

		}
	};
	if (js.Init.IsJQueryFunctionStored) {
		target.jQuery_tabs(configs);
	}
	else if (target.tabs) {
		target.tabs(configs);
	}
}
js.Init.PageLoaded = function () {
	js.Init._PageSized(true);
	js.Init.CloseLoading();
	var loadHandler = function (event) {
		if (js.Init._PageSizeHandler) window.clearTimeout(js.Init._PageSizeHandler);
		js.Init._PageSizeHandler = window.setTimeout(js.Init._PageSized, 500);
	};
	if (window.attachEvent)
		window.attachEvent('onresize', loadHandler);
	else window.onresize = loadHandler;
}
js.Init.InitValidator = function () {
	if (!$.validator) return;
	if ($.validator.messages) {
		$.validator.messages.required = "请输入内容！";
		$.validator.messages.number = "请输入有效数字！";
		$.validator.messages.email = "请输入有效的email！";
	}
	$.validator.addMethod("isInt", function (value, element) {
		if (this.optional(element)) return true;
		return !isNaN(value);
	}, "请正确填写您的值");

	$('input[valueType="int"]').validate(function () {
		var me = this;
		if (isNaN(me.value)) return false; return true;
	});
	$("form").validate();
}
//初始化客户端的alert属性
js.Init.InitClientAlertOptions = function (options) {
	if (!options) return;
	var type = options.type, isWaiting = options.isWaiting, interval = options.interval || 20,
		title = options.title || '提示', msg = options.msg, yesUrl = options.yesUrl, noUrl = options.noUrl, waitingUrl = options.waitingUrl;
	if (isWaiting) {
		if ($.messager.progress) $.messager.progress({ title: title, msg: msg, interval: 1000 });
		var checkHandle = null;
		function onFailure(data) {
			if ($.messager.progress) {
				$.messager.progress('close');
				$.messager.progress({ title: title, msg: data.msg || msg, interval: 1000 });
			}
			if (checkHandle) clearTimeout(checkHandle);
			checkHandle = setTimeout(checkUrl, interval * 1000);
		}
		function checkUrl() {
			if (waitingUrl) {
				jQuery.ajax({
					url: waitingUrl, success: function (data) {
						if (data.success) {
							if (yesUrl) js.Web.OpenWindow(this, yesUrl, null, null, null, null);
							if ($.messager.progress) $.messager.progress('close');
						} else {
							onFailure(data);
						}
					}, failure: onFailure, error: onFailure
				});
			} else {
				if ($.messager.progress) $.messager.progress('close');
			}
		}
		if (waitingUrl) checkUrl();
		else checkHandle = setTimeout(checkUrl, interval * 1000);
	} else {
		js.Web.ShowMsg(msg, title, null, function (yes) {
			if ((yes || type == 0) && yesUrl) js.Web.OpenWindow(this, yesUrl, null, null, null, null);
			else if (!yes && noUrl) js.Web.OpenWindow(this, noUrl, null, null, null, null);
		}, type);
	}
}
//初始化tooltip
js.Init.InitTooltip = function (parentSelector) {
	$('[tooltipId]').each(function (index, item) {
		var jitem = $(item), jtip = $('<span class="ui-icon ui-icon-info" style="float:left;"></span>');
		jtip.appendTo(jitem);
	});
	$('[tooltipId]').click(function () {
		var element = $(this), tooltipId = element.attr('tooltipId');
		function showTooltip(subject, body) {
			$('#alert-dialog .content').html(body);
			$('#alert-dialog').dialog({ width: 450, height: 250, title: (subject || '提示') });
			$('#alert-dialog').dialog('open');
		}
		if (tooltipId) {
			var tipCache = js._TooltipCache[tooltipId];
			if (tipCache) {
				showTooltip(tipCache.Subject, tipCache.Body);
			}
			else {
				js.Web.AjaxRequest(null, js.Web.GenerateUrl('SimpleQuery', 'OnQueryGlobalNotification', tooltipId, null, null, null, null, null, null),
				null, function (success, options) {
					if (success && options && options.tag) {
						var tag = options.tag, id = tag.Id,
							subject = (tag.Subject || '提示').trim(), body = tag.Body.trim();
						js._TooltipCache[tooltipId] = options.tag;
						showTooltip(subject, body);
						js.StopEvent(event);
						return;
					}
					else if (options && options.msg) {
						js.Web.ShowMsg(options.msg, '失败', null, null, 0);
						return;
					}
					js.Web.ShowMsg('找不到对应的帮助信息', null, null, null, 0);
				}, null, null, true, false, true);
			}
		}
		return false;
	});
}
//初始化页面
js.Init.InitPage = function (parentSelector) {
	try {
		//if (js.Sys.CheckUserAuthroizationConflict()) {
		//	var url = js.Web.GenerateUrl('Home', 'ShowSessionConflictNotice');
		//	window.open(url, '_self');
		//	return;
		//}
		//禁止后退键 作用于Firefox、Opera  
		document.onkeypress = js.Init.DisableBackspace;
		//禁止后退键  作用于IE、Chrome  
		document.onkeydown = js.Init.DisableBackspace;
		//设置md5
		js.Md5 = window.hex_md5;
		//Check browser
		if (js.Browser && js.Browser.CheckBrowser) {
			$.browser = js.Browser;
			js.Browser.CheckBrowser();
		}
		//检查是否有弹出项
		if (js.Sys.GlobalParams && js.Sys.GlobalParams.ClientAlertOptions)
			js.Init.InitClientAlertOptions(js.Sys.GlobalParams.ClientAlertOptions);
		//Init the host window
		var cWindow = window;
		while (cWindow.parent && cWindow.parent != cWindow)
			cWindow = cWindow.parent;
		js.Sys.IsHostWindow = (cWindow == window);
		js.Sys.HostWindow = cWindow;
		if (cWindow.js && cWindow.js.Caches)
			js.Caches = cWindow.js.Caches;

		js.Init.InitPreInitControls();
		if (js.Sys.EnableJqueryUI) {
			if ($.datepicker) $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
			js.Init.RenderTabs();
			//js.Init.InitTooltip();
			js.Init.InitValidator();
		}
		if (jQuery && jQuery.fn.placeholder) $(':input[placeholder]').placeholder();
		var loading_close = document.getElementById('loading-close');
		if (loading_close) loading_close.onclick = function () { js.Init.CloseLoading(); }

		$('.eui-panel').each(function (index, item) {
			var titleItem = $('.eui-panel-title .collapseable', item);
			if (titleItem.length > 0) {
				var collapsed = titleItem.hasClass('collapsed');
				var expand = titleItem.hasClass('expand');
				var options = { collapsed: collapsed, collapseable: expand || collapsed };
				$(item).data('options', options);
				if (options.collapsed) {
					$('.eui-panel-content', item).hide();
				}
			}
		});
		$('.eui-panel-title').click(function () {
			var me = this;
			var iconEl = $(me).find('.collapseable');
			if (iconEl.length <= 0) return;
			var parent = me.parentNode;
			var options = $(parent).data('options');
			if (!options.collapseable) return;
			var contentPanel = $('.eui-panel-content', parent);
			if (options.collapsed) {
				contentPanel.show();
				iconEl.removeClass('collapsed');
				iconEl.addClass('expand');
				js.Init.InitEditFields(false, contentPanel);
			}
			else {
				contentPanel.hide(200);
				iconEl.removeClass('expand');
				iconEl.addClass('collapsed');
			}
			options.collapsed = !options.collapsed;
		});
		js.Init.InitIFrame();
		//js.Init.InitEditFields();
		js.Init.RegisterPageSizeHandler(js.Init.InitEditFields);
		$('span.eui-tooltip').click(function () {
			var sender = this, jsender = $(sender), title = jsender.attr('title');
			if (title) js.Web.ShowAlertMsg(title, '提示');
		});
		if ($.fn && $.fn.tooltip) $('[data-toggle="tooltip"]').tooltip({ html: true, delay: 100 })
		js.Init.ShowRenderTimeCost();
	}
	catch (ex) {
		js.Web.ShowMsg('页面初始化失败: ' + ex);
	}
	finally {
		js.UI.HideLoadingMask();
	}
}
js.Init.InitIFrame = function () {
	var iframes = $('iframe.wrap-content');
	iframes.on('unload', function (e) {
		var me = this, frame = me, doc, jme = $(this);
		jme.css('height', null);
	});
	iframes.on('load', function (e) {
		var me = this, frame = me, doc, jme = $(this);
		try {
			jme.height(200).width(200);
			doc = frame.contentWindow.document || frame.contentDocument || window.frames[frame.id].document;
			if (doc) {
				var jdoc = $(doc), height = jdoc.outerHeight(), width = jdoc.outerWidth();
				jme.height(height + 40).width(width + 40);
			}
		} catch (e) {

		}
		if ($.messager.progress) $.messager.progress('close');
	});
}
js.Init.CloseLoading = function () { var loadingMain = 'loading-main'; $('#' + loadingMain).fadeOut('slow'); }

if ($.fn.datagrid)
	js.Override($.fn.datagrid.defaults.editors.combobox, {
		init: function (dataGrid, options, c) {
			options.onSelect = function (text, v) {
				var dataGrid = $(this);
				var options = dataGrid.data('combobox').options;
				if (options && options.relatedTextColumn) {
					var container = options.container,
						rowTr = dataGrid.parents('table').parents('tr');
					rowIndex = rowTr.attr('datagrid-row-index'),
					row = container.methods.getRows(container.el)[rowIndex],
					relatedTextColumnCell = rowTr.find('td[field=' + options.relatedTextColumn + '] .datagrid-cell'),
					selectText = dataGrid.combobox('getText');
					if (selectText) {
						row[options.relatedTextColumn] = selectText;
						relatedTextColumnCell.text(selectText);
					}
				}
			};
			var v = $.fn.datagrid.defaults.editors.combobox.override.init(dataGrid, options);
			return v;
		}
	}, true);

//验证一个container内部的html元素是否为有效的，使用的easyui的验证
js.Controls.ValidateBox = function (container) {
	var result = true;
	var o = {};
	if (!container) o = $('input.validatebox-invalid');
	else if (js.IsJQuery(container)) o = container.find('input.validatebox-invalid');
	else o = $(container).find('input.validatebox-invalid');
	o.each(function () {
		if (!$(this).validatebox('isValid')) {
			result = false;
			$(this).focus();
			return false;
		}
	});
	return result;
}
