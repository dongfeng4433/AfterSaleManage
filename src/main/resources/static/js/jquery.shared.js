var toString = Object.prototype.toString, sysParseInt = parseInt;
parseInt = function (text, radix) { return sysParseInt(text, radix ? radix : 10); }
if (!String.prototype.startsWith)
	String.prototype.startsWith = function (text) { if (!text) return false; return this.indexOf(text, 0) == 0; }
if (!String.prototype.endsWith)
	String.prototype.endsWith = function (text) {
		if (!text) return false;
		var index = this.lastIndexOf(text);
		if (index < 0) return false;
		return index + text.length == this.length;
	}
if (!String.prototype.trim)
	String.prototype.trim = function () {
		var len = this.length, s = '', ch = null, startIndex = -1, endIndex = -1;
		for (var i = 0; i < len; i++) {
			ch = this.charAt(i);
			if (ch == ' ' || ch == '\t') continue;
			startIndex = i;
			break;
		}
		if (startIndex < 0) return '';
		for (var i = len - 1; i >= 0; i--) {
			ch = this.charAt(i);
			if (ch == ' ' || ch == '\t') continue;
			endIndex = i;
			break;
		}
		return this.substring(startIndex, endIndex + 1);
	}
if (!Array.prototype.indexOf)
	Array.prototype.indexOf = function (item, fromIndex) {
		var me = this, len = me.length;
		for (var i = 0; i < len; i++)
			if (!fromIndex || i >= fromIndex)
				if (js.IsEqualTo(me[i], item, false)) return i;
		return -1;
	}
if (!Array.prototype.removeAt)
	Array.prototype.removeAt = function (index) {
		var me = this, len = me.length;
		if (index === undefined || index === null || index < 0) return null;
		var o = this[index];
		for (var i = index; i < len - 1; i++) { this[i] = this[i + 1]; }
		this.length--;
		return o;
	}
if (!Array.prototype.remove)
	Array.prototype.remove = function (item) {
		var me = this, index = this.indexOf(item);
		return this.removeAt(index);
	}
if (!Date.prototype.toLocaleDateString) {
	Date.prototype.toLocaleDateString = function () {
		var me = this;
		if (me.toDateString) return me.toDateString();
		return js.FormatDate(me, true);
	}
}
if (!Function.prototype.bind) {
	Function.prototype.bind = function (thisValue) {
		if (typeof this !== "function") {
			throw new TypeError(this + " cannot be bound as it is not a function");
		}

		// bind() also permits prepending arguments to the call
		var preArgs = Array.prototype.slice.call(arguments, 1);

		// The actual function to bind the "this" value and arguments to
		var functionToBind = this;
		var noOpFunction = function () { };

		// The "this" argument to use
		var thisArg = this instanceof noOpFunction && thisValue ? this : thisValue;

		// The resulting bound function
		var boundFunction = function () {
			return functionToBind.apply(thisArg, preArgs.concat(Array.prototype.slice.call(arguments)));
		};

		noOpFunction.prototype = this.prototype;
		boundFunction.prototype = new noOpFunction();

		return boundFunction;
	};
}
if (!Function.prototype.bind) {
	Function.prototype.bind = function (thisValue) {
		if (typeof this !== "function") {
			throw new TypeError(this + " cannot be bound as it is not a function");
		}
		// bind() also permits prepending arguments to the call
		var preArgs = Array.prototype.slice.call(arguments, 1);
		// The actual function to bind the "this" value and arguments to
		var functionToBind = this;
		var noOpFunction = function () { };
		// The "this" argument to use
		var thisArg = this instanceof noOpFunction && thisValue ? this : thisValue;
		// The resulting bound function
		var boundFunction = function () {
			return functionToBind.apply(thisArg, preArgs.concat(Array.prototype.slice.call(arguments)));
		};
		noOpFunction.prototype = this.prototype;
		boundFunction.prototype = new noOpFunction();
		return boundFunction;
	};
}
if (!window.navigate && window.open) window.navigate = window.open;
//在ie8和ie11下分别有一个函数不在的
//if (!document.addEventListener) document.addEventListener = document.attachEvent;
//if (!document.removeEventListener) document.removeEventListener = document.detachEvent;
//if (!document.attachEvent) document.attachEvent = document.addEventListener;
//if (!document.detachEvent) document.detachEvent = document.removeEventListener;
window.js = {
	DefaultTheme: 'classic', EmptyFn: function () { }, Md5: null, HtmlTemplate: {}, ExtHtmlTemplate: {},
	PreInitControls: { Forms: { _All: [] }, Buttons: { _All: [] }, TextBoxs: { _All: [] } }, Functions: {},
	Methods: { _IsInited: false }, _buttons: {}, _TooltipCache: {},
	/** Caches里存放所有的代码，缓存*/ Caches: {}, CacheQueryTags: {}, CacheQueringQueues: {}, GetCacheUrl: function (name, queryAll) {
		if (!name) return '';
		if (name.indexOf('/') >= 0) return name;
		var url = js.Sys.CacheUrls[name] || ('Query' + name);
		if (!url || !queryAll) return url;
		return js.Web.AppendUrlParams(url, { _q4all: queryAll });
	},
	ProcessCacheWithPinyin: function (ca) {
		var caItem = js.Caches[ca], array = caItem._items_;
		for (var i = 0; i < array.length; i++) {
			var titem = array[i];
			var pinyinText = titem.DisplayString;
			if (js.UI.PinYin && pinyinText) {
				pinyinText = js.UI.PinYin.CC2PY(pinyinText);
			}
			if (pinyinText)
				titem.__PinyinText = pinyinText.toUpperCase();
		}
		js.Math.Sort(array, function (a, b) {
			if (a == b) return 0;
			if (!a) return -1;
			if (!b) return 1;
			if (a.Prority == b.Prority) {
				if (a.__PinyinText < b.__PinyinText) return -1;
				if (a.__PinyinText == b.__PinyinText) return 0;
				return 1;
			}
			else if (a.Prority < b.Prority) return -1;
			else return 1;
		});
		caItem._items_ = array;
		caItem._isReady_ = true;
	},
	//加载预定义的缓存
	LoadPreloadCaches: function () {
		var url = js.Web.GenerateUrl('Home', 'QueryPreloadCaches', null, null, null, null, null, null, false, true);
		function processCache(ca, tcaItems) {
			var caItem = js.Caches[ca];
			if (js.IsArray(tcaItems)) {
				if (!caItem) js.Caches[ca] = caItem = {};
				caItem._items_ = tcaItems;
				for (var i = 0; i < tcaItems.length; i++) {
					caItem[tcaItems[i].Key] = tcaItems[i];
				}
			}
			else {
				js.Caches[ca] = caItem = tcaItems;
				var array = [];
				for (var key in caItem) {
					if (caItem[key] && caItem[key].Key) {
						array.push(caItem[key]);
					}
				}
				caItem._items_ = array;
			}
		}
		js.Web.AjaxRequest(this, url, null, function (success, data) {
			if (success && data && data.tag) {
				for (var ca in data.tag) {
					processCache(ca, data.tag[ca]);
				}
				//先处理缓存，再处理拼音
				//for (var ca in data.tag) {
				//	processCacheWithPinyin(ca);
				//}
				//js.Web.ShowAlertMsg('加载预定义缓存成功!');
				return;
			}
			js.Web.ShowAlertMsg('加载缓存失败, 如果需要请重试!');
		}, null, null, false, false, true, 60000);
	},
	GetJsonCache: function (cache_id, update_refresh_date, callback, scope) {
		var url = js.Web.GenerateUrl('Home', 'GetJsonCache');
		js.Web.AjaxRequest(this, url, { cache_id: cache_id, update_refresh_date: update_refresh_date }, function (success, data) {
			if (data && success === undefined) success = true;
			var tag = data; if (data && data.tag) tag = data.tag;
			if (js.IsString(tag))
				try { data = eval('(' + tag + ')'); } catch (ex) { }
			if (callback) callback.call(scope || this, success, tag);
		}, null, null, false, false, true);
	},
	SetJsonCache: function (cache_id, content, category_id, user_id, callback) {
		var url = null;
		if (js.IsObject(content)) content = js.ToJsonString(content, true);
		if (cache_id && cache_id.indexOf('?') >= 0) url = cache_id;
		else url = js.Web.GenerateUrl('Home', 'SetJsonCache', null, { cache_id: cache_id });
		js.Web.AjaxRequest(this, url, { content: content, category_id: category_id, user_id: user_id }, callback, null, null, false, false, true);
	},
	/**
	* 需要被后续覆盖的一个空方法，原型为function(name, foce, keys, callback), 其中keys是需要更新的
	**/
	_RefreshCache: function (name, force, keys, callback, scope) {
		var url = js.GetCacheUrl(name), me = this, queryParams = null,
			params = {}, cache = js.Caches[name], tresult = {}, tkeys = [], tkeydict = {}, now = new Date();
		if (!url) {
			if (callback) callback.call(scope || me, true, tresult, { name: name, force: force, ids: keys });
			return;
		} //如果url为空，说明该换缓存是不存在
		if (cache && !cache._isReady_ && cache._items_) js.ProcessCacheWithPinyin(name);
		if (keys && !js.IsArray(keys)) keys = [keys];
		if (callback) {
			queryParams = { callback: callback, ids: keys, force: force, name: name, scope: scope || me };
		}
		js.Each(keys, function (item, index) {
			if (!item) return;
			if (item && js.IsString(item)) item = item.trim();
			if (cache && cache[item]) tresult[item] = cache[item];
			else {
				if (item == '空' || item == '(空)') { return; }
				var tempKey = name + '_' + item, cacheTime = js.CacheQueryTags[tempKey];
				if (!cacheTime) { tkeydict[item] = item; }
					//else if (queryParams&&now.getTime() - cacheTime().getTime() < 30000 && js.CacheQueringQueues[tempKey]) {
				else if (queryParams && js.CacheQueringQueues[tempKey]) {
					//正在查询队列并且小于30秒钟，那么放到查询队列中
					js.CacheQueringQueues[tempKey].push(queryParams);
				}
			}
		});
		for (var stname in tkeydict) {
			var tempKey = name + '_' + stname;
			js.CacheQueryTags[tempKey] = now;
			if (!js.CacheQueringQueues[tempKey])
				js.CacheQueringQueues[tempKey] = [];
			tkeys.push(stname);
		}
		if (!cache) js.Caches[name] = cache = {};
		function processResult(success, result, requestParams) {
			//处理回调，那些在队列中的回调
			var tkeys = requestParams.ids, tname = requestParams.name,
				tqueue = null, trequestParams = null;
			for (var i = 0; i < tkeys.length; i++) {
				tempKey = tname + '_' + tkeys[i];
				tqueue = js.CacheQueringQueues[tempKey];
				delete js.CacheQueringQueues[tempKey];
				if (tqueue) {
					for (var j = 0; j < tqueue.length; j++) {
						trequestParams = tqueue[j];
						if (trequestParams.callback) {
							try {
								trequestParams.callback.call(trequestParams.scope, success, result, trequestParams);
							} catch (ex) {
								alert('回调时失败，附加信息:' + ex);
							}
						}
					}
				}
			}
		}
		if (tkeys.length > 0)
			js.Web.AjaxRequest(this, url, { ids: tkeys, name: name, force: force, _addRandom: false }, function (success, result, requestParams) {
				if (success && result) {
					var rows = result.rows || result.tag, len = rows.length, item, cache = js.Caches[requestParams.name], tresult = {};
					for (var i = 0; i < len; i++) {
						item = rows[i];
						cache[item.Key] = item;
						tresult[item.Key] = item;
						delete js.CacheQueryTags[requestParams.name + '_' + item.Key];
					}
					if (callback) callback.call(scope || me, success, tresult, requestParams);
					processResult(success, tresult, requestParams);
				}
				else {
					if (callback) callback.call(scope || me, false, {}, requestParams);
					processResult(false, {}, requestParams);
				}
			}, null, null, true, false, true);
		else if (callback) callback.call(scope || me, true, tresult, { name: name, force: force, ids: keys });
	},
	//此处一个空函数，等待后续覆盖
	SupportImageExts: /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([gG][iI][fF]){1}$|\.([pP][nN][gG]){1}$|\.([bB][mM][pP]){1}$/,
	//根据缓存名称和标记直接返回数据，不向服务器请求
	GetCache: function (name, key) {
		var hostWindow = js.Sys.IsHostWindow ? window : js.Sys.HostWindow;
		if (!hostWindow.js.Caches) return null;
		var cache = hostWindow.js.Caches[name];
		if (!cache) return null;
		if (cache && !cache._isReady_ && cache._items_) js.ProcessCacheWithPinyin(name);
		return cache[key];
	},
	//刷新缓存，@keys 为数组或者是单个的key, @callback 原型为function(success, result, requestParams)
	RefreshCache: function (name, force, keys, callback) {
		if (js.Sys.IsHostWindow && js._RefreshCache) return js._RefreshCache(name, force, keys, callback);
		if (js.Sys.HostWindow.js._RefreshCache) return js.Sys.HostWindow.js._RefreshCache(name, force, keys, callback);
		return false;
	}, SSL_SECURE_URL: 'about:blank', Orignal: { Date: {}, String: {} },
	InitOrignal: function () {
		js.Orignal.Date.parse = Date.parse;
		js.Orignal.String.format = String.format;
	},
	Sys: {
		DateFormatString: 'dd/MM/yyyy', DateTimeFormatString: 'dd/MM/yyyy HH:mm:ss', TimeSpanFormatString: 'HH:mm', UserId: null, UserName: null,
		GlobalParams: { Security4All: null }, HostWindow: window, IsHostWindow: false, UserData: {}, UserRelatedCacheName: 'User', OwnerRelatedCacheName: null,
		//检查Cache所需要用到的url
		CacheUrls: {}, lang: 'zh-CN', TabIndex: 0, SystemDisplayMode: 0, IsMobileDevice: false,
		sys_r: Math.round(Math.random() * 10000), InitEditFields: true,
		Debug: false, Version: 1, ControllerName: '', ActionName: '', CurrentUrl: '',
		Month: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], Currency: null, Chars: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'],
		DaysOfMonth: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31], TicksOfDay: 86400000,
		AppPath: '/', EnableJqueryUI: false, EnableTabs: false, EnableAlert: false, UITrace: false,
		ClientTimeStamp: new Date(), ServerTimeStamp: new Date(), ServerClientIntervalTicks: null,
		id: 0, GetServerTime: function () {
			if (js.Sys.ServerClientIntervalTicks === null) {
				if (typeof (js.Sys.ServerTimeStamp) == 'string') js.Sys.ServerTimeStamp = Date.parse(js.Sys.ServerTimeStamp);
				js.Sys.ServerClientIntervalTicks = js.Sys.ServerTimeStamp - js.Sys.ClientTimeStamp;
			}
			return new Date(Number(new Date()) + Number(js.Sys.ServerClientIntervalTicks));
		},
		//检查用户信息和浏览器信息，在某些包含有上级页面的时候，可能会因为相同浏览器中登录了其它账号，而本页面的登录信息已经换了
		CheckUserAuthroizationConflict: function () {
			var parentWindow = js.Web.FindWindow(window);
			if (!parentWindow || parentWindow == window ||
				!parentWindow.js || !parentWindow.js.Sys || !parentWindow.js.Sys.UserData)
				return false;
			var parentUserId = parentWindow.js.Sys.UserData.UserId;
			if (!js.Sys.UserData) return false; //当前没有登录，账号过期了
			var currentUserId = js.Sys.UserData.UserId;
			if (parentUserId != currentUserId) return true;
			return false;
		},
		/**
		* 显示调试的信息
		* @checkDebugMode 如果为true, 那么当js.Sys.Debug=false，那么不提示，否则无论结果都提示
		* @blockMode 如果为true, 那么此信息显示时，将会阻挡后续代码的执行，方便调试。如果为false, 那么仅仅提示信息
		*/
		ShowDebugAlert: function (msg, checkDebugMode, blockMode) {
			if (checkDebugMode && !js.Sys.Debug) return;
			if (blockMode !== false) { alert('[调试信息]' + msg); return; }
			js.Web.ShowAlertMsg(msg, '调试信息');
		}
	},
	/**
	* 得到一个限制的类名，这个是用于joindoo风格的js类
	* @isControl 是放在Controls还是Pages中
	* @controllerName 如果为空，那么使用js.Sys.ControllerName
	* @name 如果为空，则自动根据js.Sys.ActionName来创建一个名称，类似于xxxControl或者xxxViewPage的类名
	*/
	GetClassfiedClassName: function (isControls, controllerName, name) {
		var tname = name || js.Sys.ActionName + (isControls ? 'Control' : 'ViewPage');
		return (isControls ? 'Controls.' : 'Pages.') + (controllerName || js.Sys.ControllerName) + '.' + tname;
	},
	RunDelay: function (delayParams, timeout, callback, scope) {
		if (delayParams) window.clearInterval(delayParams);
		return window.setTimeout(callback, timeout, scope);
	},
	/*
	* 复制一个对象，也可以使用CopyObject函数
	* @o 目标的对象
	* @c 需要添加的参数
	* @defaults 默认的参数
	*/
	Apply: function (o, c, defaults) {
		if (o === true) o = {};
		if (defaults) { js.Apply(o, defaults); }
		if (o && c && typeof c == 'object') { for (var p in c) { o[p] = c[p]; } }
		return o;
	},
	//创建本页面的唯一标识
	CreateNewId: function () { js.Sys.id++; return 'js-eui-id' + js.Sys.id; },
	Server: window.Server,
	LoadingMask: { RefCount: 0, TargetCon: null, MsgCon: null, IsVisible: false, Handles: [] },
	CssColors: ['red', 'yellow', 'blue', 'orange', 'green', 'purple', 'lightRed'],
	GenerateUnquieId: function () { var bytes = []; for (var i = 0; i < 16; i++) bytes[i] = Math.round(Math.random() * 255); return js.BytesToString(bytes); },
	IsArray: function (v) { return toString.apply(v) === '[object Array]'; },
	IsNull: function (o) { if (o === undefined || o === null) return true; return false; },
	IsDate: function (v) { return toString.apply(v) === '[object Date]'; },
	IsObject: function (v) { return !!v && Object.prototype.toString.call(v) === '[object Object]'; },
	IsFunction: function (v) { return toString.apply(v) === '[object Function]'; },
	IsString: function (v) { return typeof v === 'string'; },
	IsBoolean: function (v) { return typeof v === 'boolean'; },
	IsNumber: function (v) { return typeof v === 'number' && isFinite(v); },
	IsDefined: function (v) { return typeof v !== 'undefined'; },
	IsEmpty: function (v, allowBlank) { return v === null || v === undefined || ((js.IsArray(v) && !v.length)) || (!allowBlank ? v === '' : false); },
	IsJQuery: function (v) { return v.jquery; },
	//是否为手机号码
	IsMobilePhone: function (v) {
		//var regMobile = /^0?1[3|4|5|8][0-9]\d{8}$/; return regMobile.test(v);
		var regMobile = /^0?1[0-9][0-9]\d{8}$/; return regMobile.test(v);
	},
	IsEmail: function (v) { var regEmail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; return regEmail.test(v); },
	IsCardNo: function (v) { var regCardNo = /^[1-9](\d{16}|\d{13})[0-9xX]$/; return regCardNo.test(v); },
	IsExtRecord: function (v) {
		if (!v) return false;
		if (v.get && v.set && v.modelName && v.$className && v.modelName.startsWith('Ext.data.Store')) return true;
		return false;
	},
	FormatFloat: function (src, pos) { return Math.round(src * Math.pow(10, pos)) / Math.pow(10, pos); },
	FormatDate: function (v, justDate) { if (!v) return v; if (justDate) return js.Renderer.DateRender(v); return js.Renderer.DateTimeRender(v); },
	PadNumber: function (n, percision) {
		if (n === 0 && percision > 0) {
			var s = '';
			for (var i = 0; i < percision; i++)
				s += '0';
			return s;
		}
		if (n == null || n === 0 || n < 0 || isNaN(n)) return n;
		percision = percision || 1;
		var s = n;
		if (percision > 1 && n < 10) s = '0' + s;
		if (percision > 2 && n < 100) s = '0' + s;
		if (percision > 3 && n < 1000) s = '0' + s;
		if (percision > 4 && n < 10000) s = '0' + s;
		if (percision > 5 && n < 100000) s = '0' + s;
		if (percision > 6 && n < 1000000) s = '0' + s;
		if (percision > 7 && n < 10000000) s = '0' + s;
		if (percision > 8 && n < 100000000) s = '0' + s;
		if (percision > 9 && n < 1000000000) s = '0' + s;
		return s;
	},
	//将一个字符串进行分割，按指定的分隔符
	GetSeparatedItems: function (text, sp) {
		if (!text) return [];
		if (!sp) sp = /[,;]/;
		if (js.IsString(text) == false) text = text.toString();
		return text.split(sp);
	},
	FormatIDs: function (ids, addComma, splitText) {
		if (ids == null) return "";
		if (splitText == null) splitText = ",";
		var text = '';
		for (var i = 0; i < ids.length; i++) {
			var id = ids[i];
			if (addComma) text += '\'';
			text += id;
			if (addComma) text += '\'';
			text += splitText;
		}
		if (text) return text.substr(0, text.length - splitText.length);
		return text;
	},
	//判断两个值是否相同，因为如果是日期类型可能需要特殊对待
	//@compareDateIfDateValue 如果是日期类型，那么是否仅比较日期，不比较时间
	IsEqualTo: function (a, b, compareDateIfDateValue) {
		var isSame = true;
		if (js.IsDate(a) || js.IsDate(b)) {
			//判断日期类型差一点点
			a = Date.parse(a);
			b = Date.parse(b);
			if (!b || !a) isSame = false;
			else if (compareDateIfDateValue) {
				if (js.FormatDate(a) != js.FormatDate(b)) isSame = false;
			}
			else {
				if (a.getTime() != b.getTime()) isSame = false;
			}
		}
		else if (b != a) {
			isSame = false;
		}
		return isSame;
	},
	/**
	* 根据模板对数据进行format, 得到一个字符串，如果模板不存在那么根据queryIfNotExists来判定是否需要动态从服务端取，
	* 如果是动态取得那么将会得到一个回调callback, 原型为function(success, result, templateName, templateContent)
	**/
	FormatObjectWithTemplate: function (templateName, object, queryIfNotExists, callback, scope) {
		var template = js.HtmlTemplate[templateName], me = this;
		if (queryIfNotExists && !template) {
			var url = js.Web.GenerateUrl('Template', templateName), tempDiv = $('<div style="display:none;" class="template_holder"></div>').appendTo($('body'));
			tempDiv.load(url, null, function (success) {
				if (success) {
					js.HtmlTemplate[templateName] = template = tempDiv.html();
					var result = js.FormatObject(template, object);
					if (callback) callback.call(scope || me, success, result, templateName, template);
				}
				else if (callback) callback.call(scope || me, false, null, templateName);
			});
			return null;
		}
		return js.FormatObject(template, object);
	},
	FormatObject: function (content, object, noUseDisplayString) {
		if (!content) {
			if (!object) return '';
			if (js.IsDate(object)) return object.toDateString();
			if (js.IsNumber(object)) return object;
			if (js.IsBoolean(object)) return object ? '是' : '否';
			if (js.IsFunction(object)) return object.call(this);
			return object.toString();
		}
		if (js.IsFunction(object)) return object.call(this, content);
		if (js.IsObject(object)) {
			//这里是防止object是ExtGrid的Model
			if (object.data && object.commit) object = object.data;
			if (noUseDisplayString) {
				for (var tname in object) {
					var to = null;
					if (!tname.endsWith('_DisplayString')) {
						to = object[tname + '_DisplayString'];
					}
					if (to) object[tname] = to;
				}
			}
			if (window.Ext && window.Ext.XTemplate) {
				var extTemplate = js.ExtHtmlTemplate[content];
				if (!extTemplate) {
					extTemplate = new window.Ext.XTemplate(content);
					for (var vwin in window)
						extTemplate[vwin] = window[vwin];
					js.ExtHtmlTemplate[content] = extTemplate;
				}
				var text = extTemplate.apply(object, window);
				return text;
			}
			for (var tname in object) {
				var to = object[tname];
				content = content.replaceAll('{' + tname + '}', js.FormatObject(null, to));
			}
			return content;
		}
		return content.replaceAll('{0}', js.FormatObject(object));
	},
	/**
	* 是否为可循环的项
	*/
	IsIterable: function (v) {
		if (js.IsArray(v) || v.callee) { return true; }
		if (/NodeList|HTMLCollection/.test(toString.call(v))) { return true; }
		return ((typeof v.nextNode != 'undefined' || v.item) && js.IsNumber(v.length));
	},
	//将数字转化成文本
	BytesToString: function (bytes) {
		var text = '', b = 0;
		for (var i = 0; i < bytes.length; i++) {
			b = Math.floor(bytes[i] / 16);
			if (b < 10) text += b;
			else text += js.Sys.Chars[b - 10];
			b = bytes[i] % 16;
			if (b < 10) text += b;
			else text += js.Sys.Chars[b - 10];
		}
		return text;
	},
	//对数组或者对象进行枚举
	Iterate: function (obj, fn, scope) {
		if (js.IsEmpty(obj)) { return; }
		if (js.IsIterable(obj)) {
			for (var i = 0, len = obj.length; i < len; i++)
				if (fn.call(scope || obj[i], obj[i], i, obj) === false) return;
			return;
		} else if (typeof obj == 'object') {
			for (var prop in obj) {
				if (obj.hasOwnProperty(prop)) {
					if (fn.call(scope || obj, prop, obj[prop], obj) === false) { return; };
				}
			}
		}
	},
	/*
	* 循环本数组，类似于jQuery.each.
	* @return 返回一个数组，所以本函数也可以用于将一个单个的元素变成一个数组
	* @fn, 原型为 function(item, index, array)
	*/
	Each: function (array, fn, scope, filterEmpty, autoMergeArray) {
		if (js.IsNull(array)) return [];
		if (!js.IsJQuery(array) && (!js.IsIterable(array) || !js.IsArray(array))) array = [array];
		var o = null;
		var result = [];
		if (autoMergeArray !== false) autoMergeArray = true;
		for (var i = 0, len = array.length; i < len; i++) {
			o = array[i];
			if (fn) o = fn.call(scope || o, o, i, array);
			if (js.IsNull(o) == false || filterEmpty == false) {
				if (typeof (o) != 'string' && js.IsArray(o) && autoMergeArray == true) {
					for (var j = 0; j < o.length; j++)
						if (js.IsNull(o[j]) == false || filterEmpty == false) result.push(o[j]);
				}
				else result.push(o);
			}
		}
		return result;
	},
	//复制一个对象
	CopyObject: function (source, target, overwrite, addConfigs, skipFields) {
		var skipValue = {};
		var temp;
		if (!target) target = {};
		else js.Each(skipFields, function (o, i, array) {
			if (o) {
				temp = target[o];
				if (temp !== undefined) skipValue[o] = temp;
			}
		}, this, true, false);
		if (overwrite == null || overwrite) {
			for (var v in source) {
				temp = source[v];
				if (temp == undefined)
					temp = null;
				target[v] = temp;
			}
		}
		else { for (var v in source) { if (js.IsNull(target[v])) target[v] = source[v]; } }
		if (addConfigs) for (var av in addConfigs) { temp = addConfigs[av]; if (temp == undefined) temp = null; target[av] = temp; }
		for (var v in skipValue) target[v] = skipValue[v];
		return target;
	},
	/**
	* 合成对象
	* @main 已此对象为主
	* @second 将second对象的数据复制到main上去
	* @overwrite 是否覆盖main的字段
	* @maxDeep 最多的深度，默认为5
	* @deep 当前深度，此字段请不要设置，这是本方法的私有参数
	**/
	MergeObject: function (main, second, overwrite, maxDeep, deep) {
		main = main || {};
		if (js.IsNull(maxDeep)) maxDeep = 5;
		if (js.IsNull(deep)) deep = 0;
		if (deep >= maxDeep) return main;
		var o, s;
		for (var name in second) {
			o = main[name];
			s = second[name];
			if (js.IsNull(s) == false) {
				if (js.IsNull(o)) main[name] = s;
				else {
					if (js.IsArray(o) || js.IsDate(o) || js.IsString(o) || js.IsNumber(o) || js.IsFunction(o) ||
                        js.IsBoolean(o)) {
						if (overwrite) main[name] = s;
					} else {
						if (overwrite && (js.IsArray(s) || js.IsDate(s) || js.IsString(s) || js.IsNumber(s) || js.IsFunction(s) ||
                        js.IsBoolean(s))) main[name] = s;
						js.MergeObject(o, s, overwrite, maxDeep, deep + 1);
					}
				}
			}
		}
		return main;
	},
	Extend: function () {
		var io = function (o) { for (var m in o) { this[m] = o[m]; } };
		var oc = Object.prototype.constructor;
		return function (sb, sp, overrides) {
			if (typeof sp == 'object') {
				overrides = sp;
				sp = sb;
				sb = overrides.constructor != oc ? overrides.constructor : function () { sp.apply(this, arguments); };
			}
			var F = function () { }, sbp, spp = sp.prototype;

			F.prototype = spp;
			sbp = sb.prototype = new F();
			sbp.constructor = sb;
			sb.superclass = spp;
			if (spp.constructor == oc) { spp.constructor = sp; }
			sb.override = function (o) { js.Override(sb, o); };
			sbp.superclass = sbp.supr = (function () { return spp; });
			sbp.override = io;
			js.Override(sb, overrides);
			sb.extend = function (o) { return js.Extend(sb, o); };
			return sb;
		};
	}(),
	Override: function (origclass, overrides, isObject) {
		if (overrides) {
			var p = origclass.prototype;
			if (p == null && isObject && origclass) {
				origclass.override = origclass.override || {};
				for (var sname in overrides) {
					origclass.override[sname] = origclass[sname] || function () { };
					origclass[sname] = overrides[sname];
				}
			} else {
				js.Apply(p, overrides);
				if (overrides.hasOwnProperty('toString')) {
					p.toString = overrides.toString;
				}
			}
		}
	},
	//停止一个事件
	StopEvent: function (event) {
		if (!event) return false;
		if (event.preventDefault) event.preventDefault();
		else if (window.event) event.returnValue = false;
	},
	ForbiddenKeyEvent: function (keyCode) {
		//$('body').keydown(function (event) {
		//	if (event.keyCode != keyCode) return;
		//	if (event.keyCode == 8&&window.history.length) {// 8:backspace
		//		js.StopEvent(event);
		//	}
		//});
	},
	ToJsonString: function (obj, skipNullField, indentString) {
		var thisFunc = js.ToJsonString, r = null, tempObj;
		switch (typeof (obj)) {
			case 'string': return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';
			case 'array': return '[' + obj.map(thisFunc, skipNullField, indentString).join(indentString ? ',\n' : ',') + ']';
			case 'object':
				if (obj instanceof Array) {
					var strArr = [];
					var len = obj.length;
					if (len > 0 || !skipNullField) {
						for (var i = 0; i < len; i++) {
							r = thisFunc(obj[i], skipNullField, indentString);
							if (r) strArr.push(r);
						}
						return '[' + strArr.join(indentString ? ',\n' : ',') + ']';
					}
					return '';
				}
				else if (obj == null) { return 'null'; }
				else if (js.IsDate(obj)) {
					//return '"' + (obj.toJSON ? obj.toJSON() : js.Renderer.DateTimeRender(obj))+'"';
					return '"' + (js.Renderer.DateTimeRender(obj)) + '"';
				}
				else {
					var string = [];
					for (var property in obj) {
						tempObj = obj[property];
						if (js.IsFunction(tempObj) == false && (!skipNullField || tempObj)) {
							r = thisFunc(obj[property], skipNullField, indentString);
							if (r) string.push((property.replace(/(["\\])/g, '\\$1')) + ':' + r);
						}
					}
					if (string.length <= 0) return '';
					return '{' + string.join(indentString ? ',\n' : ',') + '}';
				}
			case 'number': return obj;
			case 'boolean': return obj;
		}
		return '';
	},
	//转化成百分比
	ToPercent: function (a, b, percision) {
		if (b == 0) return 0;
		return js.ToPercision(a * 100 / b, percision);
	},
	//转化到某个精度
	ToPercision: function (v, percision) {
		if (js.IsNull(percision)) return v;
		return parseFloat(v).toFixed(percision);
	},
	//在数组中找到一个结果, singleResult为bool类型，代表是否需要返回多个结果, func的原型为bool func(object item, int index)
	FindInArray: function (array, singleResult, func) {
		if (!array.length || !func) return singleResult ? null : [];
		var result = [];
		for (var i = 0; i < array.length; i++) {
			if (func(array[i], i)) {
				if (singleResult) return array[i];
				result.push(array[i]);
			}
		}
		if (singleResult) return null;
		return result;
	},
	//判断一个字符串是否在该字符串数组中
	IsInArray: function (arr, o) {
		if (!arr && !o) return true;
		if (!arr || !o) return false;
		for (var i = 0; i < arr.length; i++) { if (arr[i] == o) { return true; } }
		return false;
	},
	//努力匹配两列字符串数组, 得到一个匹配结果的数组, 拿tA去匹配tB， 返回tA中每一个元素在tB中元素的序号
	TryMatchTwoTextArray: function (tA, tB, map) {
		var lenA = tA.length, lenB = tB.length;
		if (!map) { map = []; map.length = lenA; }
		//精确匹配
		for (var i = 0; i < lenA; i++) {
			if (map[i] === undefined) {
				for (var j = 0; j < lenB; j++)
					if (tB[j] == tA[i]) { map[i] = j; break; }
			}
		}
		//忽略大小写
		for (var i = 0; i < lenA; i++) {
			if (map[i] === undefined) {
				var lowA = tA[i].toLowerCase();
				for (var j = 0; j < lenB; j++)
					if (tB[j].toLowerCase() == lowA) { map[i] = j; break; }
			}
		}
		//模糊匹配
		for (var i = 0; i < lenA; i++) {
			if (map[i] === undefined) {
				for (var j = 0; j < lenB; j++)
					if (tA[i].indexOf(tB[j]) >= 0) { map[i] = j; break; }
			}
		}
		//模糊匹配 - 忽略大小写
		for (var i = 0; i < lenA; i++) {
			if (map[i] === undefined) {
				var lowA = tA[i].toLowerCase();
				for (var j = 0; j < lenB; j++)
					if (lowA.indexOf(tB[j].toLowerCase()) >= 0) { map[i] = j; break; }
			}
		}
		return map;
	}
}
if (!js.Browser) js.Browser = $.browser;
js.CopyObject(js.Server, js.Sys, true);
if (!js.Sys.UserData && js.Sys.GlobalParams && js.Sys.GlobalParams.UserData) js.Sys.UserData = js.Sys.GlobalParams.UserData;
js.InitOrignal();
js.Web = {
	//function(sender, url, params, callback, success, failure, isPost, showDefaultMsg, hideResponseMsg)
	AjaxRequest: function (sender, url, params, callback, success, failure, isPost, showDefaultMsg, hideResponseMsg, timeout) { },
	_ZeroClipboard: null, LastClipboardText: null,
	//统一的一个全局事件，发生在某些form提交之前可以用
	BeforeFormSubmit: function (data, formElement) {
		return true;
	},
	GetFriendlyErrorMessage: function (error, type) {
		if (error == 'Not Found') error = '服务端没有对应您请求的响应方法';
		return error;
	},
	OpenFilePreviewWindow: function (attachmentId, defaultUrl) {
		if (!attachmentId) return;
		if (!defaultUrl) defaultUrl = js.Web.GenerateUrl('Account', 'DownloadFile', null, { id: attachmentId });
		var extIndex = attachmentId.lastIndexOf('.'), ext = extIndex >= 0 ? attachmentId.substring(extIndex) : attachmentId;
		if (ext) ext = ext.toLowerCase();
		if (ext == '.png' || ext == '.jpg' || ext == '.jpeg' || ext == '.gif') {

		}
		return window.open(defaultUrl, attachmentId);
	},
	//Firefox下Clipboard的读写js脚本 
	/************************************************** 
	将字符串maintext复制到剪贴板 
	**************************************************/
	SetClipboard: function (maintext, targetElement) {
		//if (window.clipboardData) {	   
		if (false) {
			return (window.clipboardData.setData("text", maintext));
		} else {
			var cb = js.Web.GetZeroClipboard(targetElement);
			if (cb) cb.setText(maintext);
			else js.Web.ShowAlertMsg(maintext);
			return true;
		}
		return false;
	},
	_HtmlEncodeDiv: null,
	//对str进行html encode操作， 获取编号的文本
	HtmlEncode: function (str) {
		var div = js.Web._HtmlEncodeDiv;
		if (js.Web._HtmlEncodeDiv == null) {
			div = document.createElement("div");
			js.Web._HtmlEncodeDiv = div;
		}
		div.innerHTML = '';
		div.appendChild(document.createTextNode(str));
		return div.innerHTML;
	},
	//对str进行html decode操作，获取解码的文本
	HtmlDecode: function (str) {
		var div = js.Web._HtmlEncodeDiv;
		if (js.Web._HtmlEncodeDiv == null) {
			div = document.createElement("div");
			js.Web._HtmlEncodeDiv = div;
		}
		div.innerHTML = str;
		return div.innerHTML;
	},
	AppendUrlCommonVars: function (url, rand) {
		if (!url) return url;
		if (rand) {
			var match = url.match(/\?sys\_r\=[0-9\-\.]*/i);
			if (match != null) { url = url.replace(match[0], '?sys_r=' + Math.round(Math.random() * 10000)); }
			else {
				match = url.match(/\&sys\_r\=[0-9\-\.]*/i);
				if (match != null) url = url.replace(match[0], '&sys_r=' + Math.round(Math.random() * 10000));
			}
		}
		var match = url.match(/\?lang\=[0-9\-\.a-zA-Z]*/i);
		if (match != null) { url = url.replace(match[0], '?lang=' + js.Sys.lang); }
		else {
			match = url.match(/\&lang\=[0-9\-\.a-zA-Z]*/i);
			if (match != null) url = url.replace(match[0], '&lang=' + js.Sys.lang);
		}
		//url = js.Web.ResolveUrlOption(url, sys_r, 'classic');
		//url = js.Web.ResolveUrlOption(url, 'lang', js.Sys.lang);
		url = js.Web.ResolveUrlOption(url, 'theme', js.DefaultTheme);
		if (js.Sys.UserId)
			url = js.Web.ResolveUrlOption(url, '_usid', js.Sys.UserId);
		return url;
	},
	ResolveUrlOption: function (url, opt, defaultValue, forceReplace) {
		var optValue = js.Web.HasOption(opt, url, true);
		if (forceReplace) {
		}
		else if (!optValue) {
			optValue = js.Web.HasOption(opt);
			if (!optValue) optValue = defaultValue;
			var index = url.indexOf('?');
			if (index >= 0) url += '&' + encodeURIComponent(opt) + '=' + optValue;
			else url += '?' + encodeURIComponent(opt) + '=' + optValue;
		};
		return url;
	},
	GetQueryParams: function (name, path) {
		var regex = RegExp('[?&]' + name + '=([^&]*)');
		//var match = regex.exec(location.search) || regex.exec(path);
		var match = regex.exec(path || location.search);
		return match && decodeURIComponent(match[1]);
	},
	//根据url加载脚本
	LoadScript: function (url, callback) {
		if (!url) return;
		var head = window.document.getElementsByTagName('head')[0];
		var scriptNode = document.createElement('script');
		scriptNode.type = 'text/javascript';
		scriptNode.src = url;
		if (callback) {
			scriptNode.callback = callback;
			scriptNode.timeoutHandle = window.setTimeout(function () {
				if (scriptNode.callback) scriptNode.callback.call(scriptNode);
			}, 4000, scriptNode);
			scriptNode.onload = scriptNode.onreadystatechange = function () {
				if (this.readyState == 'loaded' && this.callback) {
					if (this.timeoutHandle) window.clearTimeout(this.timeoutHandle);
					this.callback(this);
				}
			}
		}
		head.appendChild(scriptNode);
	},
	LoadStyle: function (url) {
		if (!url) return;
		var head = window.document.getElementsByTagName('head')[0];
		var scriptNode = document.createElement('link');
		scriptNode.type = 'text/css';
		scriptNode.href = url;
		scriptNode.rel = 'stylesheet';
		head.appendChild(scriptNode);
	},
	//是否包含某个属性
	HasOption: function (opt, queryString, returnWhole) {
		var s = queryString || location.search;
		var re = new RegExp('(?:^|[&?])' + opt + '(?:[=]([^&]*))?(?:$|[&])', 'i');
		var m = re.exec(s);
		if (returnWhole)
			return m ? (m[0] === undefined || m[0] === '' ? true : m[0]) : false;
		return m ? (m[1] === undefined || m[1] === '' ? false : m[1]) : false;
	},
	//设置Cookie
	SetCookieValue: function (name, value) {
		var Days = 30;
		var exp = new Date();
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
	},
	//删除cookies
	DeleleCookie: function (name) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval = getCookie(name);
		if (cval != null)
			document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
	},
	//获取Cookie信息
	GetCookieValue: function (name) {
		var cookies = document.cookie.split('; '),
        i = cookies.length, cookie, value;
		while (i--) {
			cookie = cookies[i].split('=');
			if (cookie[0] === name) {
				value = cookie[1];
			}
		}
		if (value && value.indexOf('%u') >= 0) {
			return unescape(value);
		}
		return value;
	},
	/*
	* 显示一个消息, 
	* @type 0为alert, 1为prompt， 2为confirm， 3为progress(暂时不支持)
	* @icon 0为info, 1为warning, 2为error， 如果为字符串则是自定义的iconClass. 暂时不支持本参数
	* @callback 回调函数
	* @okButton 如果需要设置你的Ok按钮的文字信息，可以用这个
	* @cancelButton 如果需要设置你的Cancel按钮的文字信息，可以用这个
	* @defaultValue 默认的值，这个只有在prompt时才有效果
	*/
	ShowMsg: function (msg, title, icon, callback, type, okButton, cancelButton, defaultValue, scope) {
		title = title || '提示';
		okButton = okButton || '确定';
		cancelButton = cancelButton || '关闭';
		if (!js.IsString(msg)) {
			if (js.IsFunction(msg)) msg = msg.call(scope);
			else if (js.IsObject(msg) && msg.error) msg = msg.error;
			else msg = msg.toString();
		}
		if ($.messager) {
			$.extend($.messager.defaults, { ok: okButton, cancel: cancelButton });
			if (type == 1 || type == 4) {
				var twin = $.messager.prompt(title, msg, callback);
				if (defaultValue && twin) $(".messager-input", twin).val(defaultValue);
			}
			else if (type == 2) $.messager.confirm(title, msg, callback);
			else {
				$.messager.alert(title, msg, null, callback);
			}
		}
		else if (window.Ext && window.Ext.MessageBox) {
			var cfg = {
				title: title, minWidth: window.Ext.MessageBox.minPromptWidth, msg: msg,
				buttons: window.Ext.MessageBox.OKCANCEL, callback: null, scope: scope || this, multiline: null, value: null
			};
			if (type == 1 || type == 4) {
				cfg.prompt = true;
				cfg.buttons = window.Ext.MessageBox.OKCANCEL;
				cfg.buttonText = { ok: okButton, cancel: cancelButton };
				cfg.callback = function (result, tvalue) {
					if (result == 'ok') {
						if (callback) callback(true, tvalue);
					} else if (callback) callback(false, tvalue);
				};
				window.Ext.MessageBox.prompt(cfg);
			}
			else if (type == 2) {
				//cfg.icon = window.Ext.MessageBox.QUESTION;
				cfg.buttons = window.Ext.MessageBox.YESNO;
				cfg.buttonText = { yes: okButton, no: cancelButton };
				cfg.callback = function (result) {
					if (result == 'yes') { if (callback) callback(true); }
					else if (callback) callback(false);
				};
				window.Ext.MessageBox.confirm(cfg);
			}
			else if (type == 3) {

			}
			else {
				cfg.minWidth = window.Ext.MessageBox.minWidth;
				cfg.buttons = window.Ext.MessageBox.OK;
				cfg.fn = function (result) { if (callback) callback(true); };
				//'ok', 'yes', 'no', 'cancel'
				cfg.buttonText = { ok: okButton };
				window.Ext.MessageBox.alert(cfg);
			}
		}
		else {
			var r = true;
			if (type == 1) r = prompt(msg, defaultValue);
			else if (type == 2) r = confirm(msg);
			else alert(msg);
			if (callback) callback(r);
		}
	},
	ShowAlertMsg: function (msg, title, icon, callback, okButton, scope) {
		return js.Web.ShowMsg(msg, title, icon, callback, 0, okButton, null, null, scope);
	},
	ShowConfirmMsg: function (msg, title, icon, callback, okButton, cancelButton, scope) {
		return js.Web.ShowMsg(msg, title, icon, callback, 2, okButton, cancelButton, null, scope);
	},
	ShowPromptMsg: function (msg, title, icon, callback, okButton, cancelButton, defaultValue, scope) {
		return js.Web.ShowMsg(msg, title, icon, callback, 1, okButton, cancelButton, defaultValue, scope);
	},
	ShowMultiLinePromptMsg: function (msg, title, icon, callback, okButton, cancelButton, defaultValue, scope) {
		return js.Web.ShowMsg(msg, title, icon, callback, 4, okButton, cancelButton, defaultValue, scope);
	},
	//显示一个专门用于呈现错误的消息框
	ShowErrorMsg: function (ex, mainMsg, title, callback, okButton) {
		return js.Web.ShowMsg((mainMsg || '') + ex, title, null, callback, 0, okButton);
	},
	/**
	* 添加一个ajax标识, url只是用来参考的url
	* @header ajax请求头
	* @addRandom 是否添加随机参数
	**/
	AppendAjaxHeader: function (header, url, addRandom) {
		var params = { 'X-Requested-With': 'XMLHttpRequest' };
		if (addRandom !== false) {
			params.sys_r = Math.round(Math.random() * 10000);
		}
		if (typeof (header) == 'string')
			return js.Web.AppendUrlParams(header, params);
		var params = js.Apply(params, header);
		return js.Web.RemoveExistsUrlComps(params, url);
	},
	/**
	* 找到一个Window, 如果windowName不提供则找到最上层的window对象
	* @currentWin 当前的window
	**/
	FindWindow: function (currentWin, windowName) {
		if (windowName == null) {
			var parent = currentWin.parent;
			while (window && parent && parent != currentWin)
			{ currentWin = parent; parent = currentWin.parent; }
		}
		else {
			var twin = window.frames[windowName];
			if (twin != null && twin.contentWindow) currentWin = twin.contentWindow;
			else if (twin != null && twin == twin.window) currentWin = twin;
		}
		return currentWin;
	},
	/**
	* 查找到最近的一个form
	**/
	FindForm: function (sender) {
		var element = sender;
		var s;
		while (element) {
			s = element.nodeName.toLowerCase();
			if (s == 'form' || s == 'window') break;
			element = element.parentNode;
		}
		if (element && s == 'form')
			return element;
		return null;
	},
	//在主框架中打开Url
	OpenUrlInMainFrame: function (url, text, forceOpen, cls, id, clientParams) {
		var mainFrame = js.Init.GetMainFrame();
		if (mainFrame && mainFrame.OpenUrl) {
			return mainFrame.OpenUrl(url, text, forceOpen, cls, id, clientParams);
		}
		else js.Web.OpenWindow(this, url);
	},
	//打开或者导航到一个新的页面, isMainFrame代表是否在MainFrame里打开
	OpenWindow: function (sender, url, target, attrs, addAllQueryString, event) {
		var queries = window.location.search;
		var index;
		if (addAllQueryString) {
			index = url.indexOf('?');
			if (index >= 0) {
				url += '&' + queries;
			} else url += '?' + queries;
		}
		if (url) {
			url = js.Web.AppendUrlCommonVars(url, false);
			if ((!target || target == '_self')) {
				js.UI.ShowLoadingMask('正在打开页面，请稍候');
			}
			else js.UI.ShowLoadingMask('正在打开页面，请稍候', target == '_blank' ? 1000 : 5000);
			try {
				window.open(url, target || '_self', attrs || "");
			}
			catch (error) {
				js.Sys.ShowDebugAlert('在当前浏览器下打开页面出错, 错误:' + error, true, true);
				window.open(url, '_blank', attrs || "");
			}
		}
		if (event) js.StopEvent(event);
	},
	OpenFullSizeWindow: function (url) {
		js.Web.OpenWindow(null, url, '_blank', "");
	},
	OpenModalDialog: function (url, name, config) {
		var obj = new Object();
		obj.name = name;
		window.showModalDialog(url, obj, config);
	},
	OpenCustomSizeWindow: function (name, innerWidth, innerHeight) {
		var ScreenWidth = screen.availWidth
		var ScreenHeight = screen.availHeight
		var StartX = (ScreenWidth - innerWidth) / 2
		var StartY = (ScreenHeight - innerHeight) / 2
		window.open('about:blank', name, 'left=' + StartX + ', top=' + StartY + ', Width=' + innerWidth + ', height=' + innerHeight + ', resizable=yes, scrollbars=yes, status=yes, toolbar=no, menubar=no, location=no');
	},
	OpenPostWindow: function (url, data, name, innerWidth, innerHeight) {
		var tempForm = js.Web.CreatePostForm(data, null, url, name);
		tempForm.submit();
		document.body.removeChild(tempForm);
	},
	CreatePostForm: function (postData, id, action, target) {
		var tempForm = document.createElement("form"), hiddens = [];
		tempForm.id = id || js.GenerateUnquieId(),
    	tempForm.method = "post";
		tempForm.action = action;
		tempForm.target = target;
		addField = function (name, value) {
			var hiddenItem = document.createElement('input');
			var jhiddenItem = $(hiddenItem).attr({ type: 'hidden', value: value, name: name });
			tempForm.appendChild(hiddenItem);
			hiddens.push(hiddenItem);
		};
		addFields = function (ovalue, prefix) {
			if (js.IsArray(ovalue)) {
				for (var i = 0; i < ovalue.length; i++)
					addFields(ovalue[i], (prefix || '') + '[' + i + ']');
			}
			else if (js.IsObject(ovalue) && !js.IsDate(ovalue) && !js.IsString(ovalue)) {
				for (var tname in ovalue) {
					var tvalue = ovalue[tname];
					if (!tvalue || js.IsDate(tvalue) || js.IsString(tvalue) || js.IsNumber(tvalue) || js.IsBoolean(tvalue)) {
						addField(prefix ? prefix + '.' + tname : tname, tvalue);
					} else if (js.IsArray(tvalue) || js.IsObject(tvalue)) {
						addFields(tvalue, (prefix ? prefix + tname : tname));
					}
				}
			}
		}
		document.body.appendChild(tempForm);
		addFields(postData);
		return tempForm;
	},
	_AjaxSuccess: function (options, callback) {
		if (callback) callback(true, options);
	},
	_AjaxFailure: function (options, callback) {
		if (callback) callback(false, options);
	},
	_ChangePostUrl: function (url) {
		if (!url) return null;
		url = js.Web.AppendUrlCommonVars(url, true);
		var match = url.match(/\?PageNumber\=[0-9]*/i);
		if (match != null) { url = url.replace(match[0], '?PageNumber=0'); }
		else {
			match = url.match(/\&PageNumber\=[0-9]*/i);
			if (match != null) url = url.replace(match[0], '&PageNumber=0');
		}
		return url;
	},
	//生成一个可以用于贴在Excel中的字符串，放在剪贴板中, justNameMap代表是否仅仅转化nameMap中的数据
	//nameMap可以为空，也可以是一个对象数组(ExtJs的columns)，或者只是一个<string, string>的字典
	CopyToExcel: function (array, firstLineIsHeader, setClipboard, nameMap, justNameMap, targetElement) {
		var me = this, cols = null, rows = [], text = '', r, sname;
		if (!js.IsArray(array)) array = [array];
		//一个转化程序处理每一个对象, columnMeta为该元素对应的列信息
		function encode(o, record, column) {
			var v = o, vt = null;
			if (column && column.xtype == 'actioncolumn') v = column.text;
			else if (column && column.renderer) vt = column.renderer.call(me, v, column, record, -1, i, me.store, me);
			if (vt) v = vt;
			if (!v) return '';
			if (js.IsDate(v)) v = js.FormatDate(v, false);
			else v = v.toString();
			var stext = '', hasComma = false;
			for (var i = 0; i < v.length; i++) {
				if (v[i] == '"') { stext += '""'; hasComma = true; }
				else if (v[i] == '\0') { }
				else if (v[i] != '\r') { stext += v[i]; hasComma = true; }
			}
			if (hasComma) return '"' + stext + '"';
			return stext;
		}
		//处理头部和列信息
		function resolveCols(firstRow) {
			cols = [];
			if (!nameMap) {
				if (!firstRow) return;
				firstRow = firstRow.data;
				for (var tname in firstRow) {
					cols.push({ text: tname, dataIndex: tname });
				}
				return;
			}
			if (js.IsArray(nameMap)) {
				for (var i = 0; i < nameMap.length; i++) {
					if (!nameMap[i].hidden)
						cols.push(nameMap[i]);
				}
				return;
			}
			for (var tname in nameMap) {
				var tvalue = nameMap[tname];
				if (js.IsString(tvalue))
					cols.push({ text: tvalue, dataIndex: tname });
				else if (!tvalue) cols.push({ text: tname, dataIndex: tname });
				else {
					if (!tvalue.dataIndex) tvalue.dataIndex = tname;
					if (!tvalue.text) tvalue.text = tvalue.dataIndex;
					cols.push(tvalue);
				}
			}
		}
		for (var i = 0; i < array.length; i++) {
			r = array[i];
			if (r) {
				if (r.data && r.get) r = r.data;//如果是ExtJS数据，那么直接取data
				if (!cols) {
					resolveCols(r);//处理头部
					if (firstLineIsHeader) {
						for (var j = 0; j < cols.length; j++) {
							sname = cols[j].text;
							text += (encode(sname, null, cols[j]) + '\t');
						}
						text += '\r\n';
					}
				}
				for (var j = 0; j < cols.length; j++) {
					var textTemp = (encode(r[cols[j].dataIndex], r, cols[j]) + '\t');
					text += textTemp;
				}
				text += '\r\n';
			}
		}
		if (setClipboard && text) {
			//window.clipboardData.setData('text', text);		   
			//js.Web.SetClipboard(text, targetElement);
			//js.Web.ShowMsg('数据已经复制在剪贴板中，您可以粘贴在Excel表格中');
			//var name ="copytext-"+ $(targetElement).attr('name');
			//if ($('textarea[id="' + name + '"]').length > 0) {
			//    $('textarea[id="' + name + '"]').val(text);
			//} else {
			//    $(targetElement).parents().find('div.shortcut-menu').append('<textarea id="' + name + '" style="display:none">'+text+'</textarea>');
			//}
			//text = js.Web.HtmlEncode(text);
			js.Web.LastClipboardText = text;
		}
		return text;
	},
	//将从Excel中复制的数据转化成table, firstLineIsHeader代表第一行是否为表头， 返回是一个二维数组，
	ParseFromExcel: function (stext, firstLineIsHeader) {
		if (!stext) return [];//return empty if the stext is empty
		var rows = [], cols = [], tcurrent, maxCols = 0,
			text = '', srow, tcount = 0, tch, obj = [], lastComma = false, nrows = [];
		function isRowEmpty(row) {
			for (var k = 0; k < row.length; k++) if (row[k]) return false;
			return true;
		}
		function isColEmpty(targetRows, colIndex) {
			for (var k = 0; k < targetRows.length; k++)
				if (targetRows[k] && targetRows[k][colIndex]) return false;
			return true;
		}
		//add '\n' if the end is not '\n'
		if (stext[stext.length - 1] != '\n') stext += '\n';
		for (var i = 0; i < stext.length; i++) {
			tch = stext.substr(i, 1);
			if ((tch == '\t' || tch == '\n') && (tcount % 2 == 0)) {
				//Remove the '\r' char
				if (text.length > 0 && text[text.length - 1] == '\r')
					text = text.substr(0, text.length - 1);
				obj.push(text.trim());
				tcount = 0;
				text = '';
				lastComma = false;
				if (tch == '\n') {
					//Ingore the empty row
					if (isRowEmpty(obj) == false) {
						if (obj.length > maxCols) maxCols = obj.length;
						rows.push(obj);
					}
					obj = [];
				}
			} else if (tch == '"' && lastComma) {
				tcount++;
				text += '"';
				lastComma = false;
			}
			else if (tch == '"' && !lastComma) {
				tcount++;
				lastComma = true;
			}
			else text += tch;
		}
		//remove the empty column
		while (maxCols >= 0) {
			if (isColEmpty(rows, maxCols - 1) == false) break;
			maxCols--;
		}
		//adjust the rows
		for (var i = 0; i < rows.length; i++)
			rows[i].length = maxCols;
		//如果第一行不是表头或者列表为空，那么动态插入一个
		if (!firstLineIsHeader || rows.length <= 0) {
			obj = [];
			for (var i = 0; i < maxCols; i++)
				obj.push('F' + i);
			var maxRowsCount = rows.length;
			rows.length = maxRowsCount + 1;
			for (var i = maxRowsCount - 1; i >= 0; i--)
				rows[i + 1] = rows[i];
			rows[0] = obj;
		}
		else {  //adjust the columns
			cols = rows[0];
			for (var i = 0; i < cols.length; i++)
				if (!cols[i]) cols[i] = 'F' + i;
		}
		return rows;
	},
	//调整一个url的路径
	AdjustUrlPath: function (url, lang) {
		if (!url) return null;
		if (url.startsWith('~/')) url = js.Sys.AppPath + '/' + url.substr(2);
		else if (url.startsWith('/')) url = js.Sys.AppPath + url;
		else if (url.startsWith('http://') || url.startsWith('https://') || url.startsWith('ftp://')) {
		}
		else url = js.Sys.AppPath + url;
		url = url.replaceAll('//', '/').replaceAll('//', '/');
		return url;
	},
	GenerateAbsoluteUrl: function (relativeUrl, addExtendParams) {
		if (!relativeUrl) return null;
		if (relativeUrl.startsWith('~')) relativeUrl = relativeUrl.substring(1);
		var url = js.Sys.AppPath + '/' + relativeUrl;
		if (addExtendParams) {
			routeValues = js.CopyObject({
				lang: js.Web.HasOption('lang', null, false) || js.Sys.lang || 'zh-CN',
				sys_v: js.Web.HasOption('sys_v', null, false) || js.Sys.sys_v,
				theme: js.Web.HasOption('theme', null, false) || js.DefaultTheme,
				sys_r: js.Sys.sys_r
			}, routeValues, false);
			url = js.Web.EncodeUrl(routeValues, url).replace('?&', '?');
		}
		url = url.replaceAll('//', '/').replaceAll('//', '/');
		return url;
	},
	//创建一个url, 注意isListDirectly参数请参考服务器的BaseQueryModel里的IsListDirectly参数
	//@includeReturnUrl 可以为bool或者string类型，如果为true则带入本页面的地址，如果为string，则作为ReturnUrl带入生成的Url
	//@ingoreCurrentReturnUrl 如果当前包含ReturnUrl, 那么是否强制忽略掉
	GenerateUrl: function (controller, action, id, routeValues, routeName, path, useModel, isListDirectly, includeReturnUrl, ingoreCurrentReturnUrl, ingoreExtraUrlParams) {
		if (useModel) {
			if (controller != null && controller.useModel) return controller;
			return { controller: controller, action: action, id: id, routeValues: routeValues, routeName: routeName, path: path, useModel: true };
		}
		else if (controller != null && controller.useModel) {
			action = controller.action;
			id = controller.id;
			routeValues = controller.routeValues;
			routeName = controller.routeName;
			path = controller.path;
			controller = controller.controller;
		}
		else if (routeValues) {
			if (controller == undefined) controller = routeValues.controller;
			if (action == undefined) action = routeValues.action;
			if (id == undefined) id = routeValues.id;
			if (routeName == undefined) routeName = routeValues.routeName;
			if (path == undefined) path = routeValues.path;
		}
		if (routeValues) {
			delete routeValues.controller;
			delete routeValues.action;
			delete routeValues.id;
			delete routeValues.routeName;
			delete routeValues.path;
		}
		controller = controller || js.Sys.ControllerName;
		action = action || js.Sys.ActionName;
		var app_base_path = js.Sys.AppPath;
		if (routeName) { routeName = app_base_path + '/' + routeName; }
		else routeName = app_base_path;
		var url = routeName + '/' + controller + '/' + action;
		//if (action == "Index") url = routeName + "/" + controller;
		if (path && path.length > 0) url = url + '/' + path;
		if (!ingoreExtraUrlParams) {
			routeValues = js.CopyObject({
				lang: js.Web.HasOption('lang', null, false) || js.Sys.lang || 'zh-CN',
				sys_v: js.Web.HasOption('sys_v', null, false) || js.Sys.sys_v,
				theme: js.Web.HasOption('theme', null, false) || js.DefaultTheme,
				_oc: js.Sys.ControllerName,
				_oa: js.Sys.ActionName,
				_usid: js.Sys.UserId,
				sys_r: js.Sys.sys_r
			}, routeValues, false);
		}
		else if (js.IsObject(ingoreExtraUrlParams)) routeValues = js.CopyObject(routeValues || {}, ingoreExtraUrlParams, false);
		if (!js.Sys.UserId) delete routeValues._usid;
		if (id) routeValues.id = id;
		if (includeReturnUrl) {
			if (js.IsString(includeReturnUrl))
				routeValues.ReturnUrl = includeReturnUrl;
			else routeValues.ReturnUrl = window.location.pathname + window.location.search;
		}
		else if (!ingoreCurrentReturnUrl) {
			var returnUrl = js.Web.GetQueryParams('ReturnUrl', window.location.href);
			if (returnUrl && !routeValues.ReturnUrl) {
				if (returnUrl.endsWith('#')) routeValues.ReturnUrl = returnUrl.substr(0, returnUrl.length - 1);
				else routeValues.ReturnUrl = returnUrl;
			}
		}
		if (isListDirectly) routeValues.IsListDirectly = true;
		url = js.Web.EncodeUrl(routeValues, url).replace('?&', '?');
		url = url.replaceAll('//', '/').replaceAll('//', '/').replace('/?', '?');
		if (url.endsWith('#')) url = url.substr(0, url.length - 1);
		return url;
	},
	//检测一个link是否为空连接
	IsBlankLink: function (sender, href) {
		if (!href) href = sender.href;
		if (!href || href == '#' || href.indexOf('javascript:void(') >= 0) return true;
		var vmatch = /href\=\"([a-zA-Z0-9\#\?\:\/\_\=\&\;\%\-\.\(\)])*\"/i;
		var s = sender.outerHTML.match(vmatch);
		if (s == null || !s[1] || s[1] == '#') return true;
		return false;
	},
	//找到该连接最近的form
	FindForm: function (sender) {
		var element = sender;
		var s;
		while (element) {
			s = element.nodeName.toLowerCase();
			if (s == 'form' || s == 'window')
				break;
			element = element.parentNode;
		}
		if (element && s == 'form') return element;
		return null;
	},
	//将divIdOrElement所指定的元素的innerHTML放到剪贴板中
	CopyHtmlContent: function (divIdOrElement) {
		if (js.IsString(divIdOrElement)) divIdOrElement = document.getElementById(divIdOrElement);
		if (divIdOrElement) {
			var text = divIdOrElement.innerHTML;
			//if (window.clipboardData) { window.clipboardData.setData('text', text); js.Web.ShowMsg('INFO_ACTION_COPY_SUCCESS'); }
			//else js.Web.ShowMsg('INFO_NOTSUPPORT_BROWSER');
			if (js.Web.SetClipboard(text)) js.Web.ShowMsg('INFO_ACTION_COPY_SUCCESS');
			else js.Web.ShowMsg('INFO_NOTSUPPORT_BROWSER');
		}
		else js.Web.ShowMsg('INFO_NOTFOUND_CONTENT!');
	},
	//在url上添加一个params参数
	AppendUrlParams: function (url, params, justString) {
		if (js.IsNull(justString)) justString = true;
		return js.Web.EncodeUrl(params, url, null, null, null, justString);
		//return Sys.Net.WebRequest._createUrl(url, params, null);
	},
	//参考url， 将params中的一些重复的参数移除，如果url为空，则直接返回
	RemoveExistsUrlComps: function (params, url) {
		if (!url || !params) return params;
		var checkParams = ['sys_v', 'sys_r', 'lang', 'theme', 'orignal_controller', 'orignal_action', '_oc', '_oa', '_usid'],
				name = null, regex = null, matchs = null;
		for (var i = 0; i < checkParams.length; i++) {
			name = checkParams[i];
			regex = RegExp('[?&]' + name + '=([^&]*)');
			matchs = regex.exec(url);
			if (matchs) delete params[name];
		}
		return params;
	},
	//获取url中的参数
	GetUrlParmas: function (url) {
		if (!url || url == "") return {};
		if (url.indexOf('?') >= 0) url = url.substr(url.indexOf("?") + 1);
		var params = {};
		var arr = url.split("&");
		for (var i = 0; i < arr.length; i++) {
			if (arr[i].indexOf("=") <= 0) continue;
			var o = arr[i].split("=");
			params[o[0]] = o[1];
		}
		return params;
	},
	//对url进行encode
	EncodeUrl: function (o, pre, prefixName, deep, maxDeep, justString) {
		if (!o) return pre;
		if (pre && !deep) { //检查某些特殊的参数，不让这些参数重复
			o = js.Web.RemoveExistsUrlComps(o, pre);
		}
		var empty, buf = [], e = encodeURIComponent, val;
		prefixName = prefixName || '';
		if (!deep) deep = 0;
		if (!maxDeep) maxDeep = 4;
		try {
			js.Iterate(o, function (key, item) {
				empty = js.IsEmpty(item);
				if (typeof (item) == 'string' && item.length <= 0)
					empty = false;
				if (js.IsArray(item)) {
					if (!justString) {
						for (var i = 0; i < item.length; i++) {
							val = item[i];
							if (typeof (val) == 'object' && js.IsDate(val) == false && deep < maxDeep) {
								buf.push('&', js.Web.EncodeUrl(val, '', prefixName + key + '[' + i + '].', deep + 1, maxDeep));
							} else {
								buf.push('&', e(prefixName + key), '=', (!js.IsEmpty(val) && (val != key || !empty)) ? (js.IsDate(val) ? val.encode().replace(/"/g, '') : e(val)) : '');
							}
						}
					} else {
						for (var i = 0; i < item.length; i++) {
							val = item[i];
							if (typeof (val) == 'string' || typeof (val) == 'boolean' || typeof (val) == 'number') {
								buf.push('&', prefixName + key + '[' + i + '].', '=', e(val));
							} else if (js.IsDate(val)) {
								buf.push('&', prefixName + key + '[' + i + '].', '=', val.encode().replace(/"/g, ''));
							}
						}
					}
				}
				else if (item === null) { }
				else {
					js.Each(empty ? key : item, function (val) {
						if (justString) {
							if (typeof (val) == 'string' || typeof (val) == 'boolean' || typeof (val) == 'number') buf.push('&', e(prefixName + key), '=', e(val));
							else if (js.IsDate(val)) buf.push('&', e(prefixName + key), '=', val.encode().replace(/"/g, ''));
						}
						else {
							if (typeof (val) == 'object' && js.IsDate(val) == false && deep < maxDeep) {
								buf.push('&', js.Web.EncodeUrl(val, '', prefixName + key + '.', deep + 1, maxDeep));
							}
							else {
								buf.push('&', e(prefixName + key), '=', (!js.IsEmpty(val) && (val != key || !empty)) ? (js.IsDate(val) ? val.encode().replace(/"/g, '') : e(val)) : '');
							}
						}
					});
				}
			});
			if (!pre) { buf.shift(); pre = ''; }
		} catch (ex) { js.Web.ShowMsg(ex, "错误", null, null, 0); }
		var posIndex = pre.indexOf('?');
		var finalUrl = null;
		if (posIndex >= 0) {
			if (pre[pre.length - 1] == '&') finalUrl = pre.substr(0, pre.length - 1) + buf.join('');
			else finalUrl = pre + buf.join('');
		}
		else if (pre) finalUrl = pre + '?' + buf.join('');
		else finalUrl = pre + buf.join('');

		finalUrl = finalUrl.replace('?&', '?').replace('//', '/');
		if (finalUrl.startsWith('http:/')) finalUrl = finalUrl.replace('http:/', 'http://');
		//return js.Web.CheckUrlParams(finalUrl);
		return finalUrl;
	},

	jQueryParamFunction: function (a, traditional) {
		return js.Web.EncodeUrl(a, null, null, null, null, false);
		//return Sys.Net.WebRequest._createUrl(null, a, null);
	},
	//给所有的字段都添加特殊的函数
	AttachWithSpecialFunction: function (tgrid, fields, autoListenerFunction, scope) {
		fields = js.Each(fields, function (m) { return m; }, this, true, false);
		var field;
		for (var i = 0; i < fields.length; i++) {
			field = fields[i];
			if (autoListenerFunction && !field.hasListener('specialkey')) {
				field.addListener('specialkey', function (field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if (autoListenerFunction == true && tgrid.loadCall)
							tgrid.loadCall.call(scope || tgrid, tgrid);
						else if (typeof (autoListenerFunction) == 'function') autoListenerFunction.call(scope || tgrid, tgrid, field, e);
					}
				});
			}
		}
	},
	//创建一个显示帮助的图标，点击该图标可以显示相应的tooltip出来，支持ajax tip, 普通的tip或者是打开新页面的tip
	CreateTooltipIcon: function (url, ttooltip, dataWidth, dataHeight, dataTitle, target, configs) {
		if (!url && !ttooltip) return '';
		var onclick = 'javascript:js.Web._AjaxTooltipRenderClicked.call(this)';
		configs = configs || {};
		if (dataHeight) configs.height = dataHeight;
		if (dataWidth) configs.width = dataWidth;
		if (dataTitle) configs.title = dataTitle;
		if (target) configs.target = target;
		if (js.IsFunction(url)) {
			configs = configs || {};
			configs.url_id = js.GenerateUnquieId();
			js.Functions[configs.url_id] = url;
			url = '#';
		}
		else if (configs.target && configs.target != '_self') onclick = null;
		if (!onclick) return String.format('<a class="renderer-href renderer-help-link icon icon-help" href="{0}" title="{1}" target="{2}"></span>', url, ttooltip, configs.target);
		return String.format('<span class="renderer-href renderer-help-link icon icon-help" url="{0}" title="{1}" data-configs=\'{2}\' onclick="' + onclick + '"></span>', url, ttooltip, js.ToJsonString(configs, true));
	},
	_AjaxTooltipRenderWindow: null,
	_AjaxTooltipRenderClicked: function () {
		var jsMe = js.Renderer, me = this, jthis = $(this), tooltip = jthis.attr('title'), url = jthis.attr('url'), tconfigs = jthis.attr('data-configs'), configs = null;
		try {
			configs = eval('(' + tconfigs + ')');
		}
		catch (error) {

		}
		if (!configs) configs = {};
		var width = Number(configs.width) || 600, height = Number(configs.height) || 400, title = configs.title || '查看详细提示';
		if (!jsMe._AjaxTooltipRenderWindow) {
			var loadingImgSrc = js.Web.GenerateAbsoluteUrl('Content/images/Info_LoadingBig.gif'), innerHtml = '<div class="ajax-tooltip-container"><img class="ajax-tooltip-loading" src="' + loadingImgSrc + '"/><div class="ajax-tooltip-body"></div></div>';
			if (window.Ext) {
				jsMe._AjaxTooltipRenderWindow = new Ext.WindowEx({ width: 600, height: 400, title: '查看详细提示', html: innerHtml });
			}
			else {
				js.Web.ShowAlertMsg('对不起，暂时不支持!'); return;
			}
		}
		//有可能是extjs的元素，可以根据id来获取该元素
		if (configs.element && window.Ext) configs.element = Ext.getCmp(configs.element);
		if (url == '#' && configs.url_id) {
			//处理传递的url方法
			url = js.Functions[configs.url_id];
			if (url) url = url.call(configs.element || this);
		}
		if (url == '#') url = null;
		if (!url && !tooltip) {
			js.Web.ShowAlertMsg('没有可用的详细提示信息');
			return;
		}
		if (configs.target && configs.target != '_self') {
			js.Web.OpenWindow(this, url, configs.target);
			return;
		}
		if (window.Ext) { jsMe._AjaxTooltipRenderWindow.setWidth(width); jsMe._AjaxTooltipRenderWindow.setHeight(height); jsMe._AjaxTooltipRenderWindow.setTitle(title); }
		if (!url && js.Init.TooltipElement && !configs.width) {
			//'<br/><span>觉得这个提示不够，<a href="javascript:alert(\'test\')">请点击这里</a></span>'
			js.Init.TooltipElement.update(tooltip);
			var pos = jthis.offset();
			js.Init.TooltipElement.showAt([pos.left + 20, pos.top + 20]);
			return;
		}
		jsMe._AjaxTooltipRenderWindow.show();
		jsMe._AjaxTooltipRenderWindow.center();
		var ajaxTooltipContainer = $('div.ajax-tooltip-container'),
			ajaxTooltipLoadingImage = ajaxTooltipContainer.find('img.ajax-tooltip-loading'),
			ajaxTooltipBody = ajaxTooltipContainer.find('div.ajax-tooltip-body');
		if (url) {
			ajaxTooltipBody.removeClass('content-local');
			ajaxTooltipLoadingImage.show();
			ajaxTooltipBody.hide();
			ajaxTooltipBody.load(url, function () {
				ajaxTooltipBody.show();
				ajaxTooltipLoadingImage.hide();
			});
		}
		else {
			ajaxTooltipBody.addClass('content-local');
			ajaxTooltipLoadingImage.hide();
			ajaxTooltipBody.html(tooltip);
		}
	}
}
if (window.jQuery) window.jQuery.param = js.Web.jQueryParamFunction;
if (!String.prototype.replaceAll) String.prototype.replaceAll = function (s1, s2) { return this.replace(new RegExp(s1, "gm"), s2); }
if (!String.format) String.format = function (format, args, useLocale) {
	var result = '';
	for (var i = 0; ;) {
		var open = format.indexOf('{', i);
		var close = format.indexOf('}', i);
		if ((open < 0) && (close < 0)) {
			result += format.slice(i);
			break;
		}
		if ((close > 0) && ((close < open) || (open < 0))) {
			if (format.charAt(close + 1) !== '}') {
				throw 'format格式不匹配';
			}
			result += format.slice(i, close + 1);
			i = close + 2;
			continue;
		}
		result += format.slice(i, open);
		i = open + 1;
		if (format.charAt(i) === '{') {
			result += '{';
			i++;
			continue;
		}
		if (close < 0) throw 'format格式不匹配';
		var brace = format.substring(i, close);
		var colonIndex = brace.indexOf(':');
		var argNumber = parseInt((colonIndex < 0) ? brace : brace.substring(0, colonIndex), 10) + 1;
		if (isNaN(argNumber)) throw Error.argument('format', Sys.Res.stringFormatInvalid);
		var argFormat = (colonIndex < 0) ? '' : brace.substring(colonIndex + 1);
		var arg = args[argNumber];
		if (typeof (arg) === "undefined" || arg === null) {
			arg = '';
		}
		if (arg.toFormattedString) {
			result += arg.toFormattedString(argFormat);
		}
		else if (useLocale && arg.localeFormat) {
			result += arg.localeFormat(argFormat);
		}
		else if (arg.format) {
			result += arg.format(argFormat);
		}
		else
			result += arg.toString();
		i = close + 1;
	}
	return result;
}
js.Apply(Date, {
	parseFunctions: {
		"M$": function (input, strict) {
			var re = new RegExp('\\/Date\\(([-+])?(\\d+)(?:[+-]\\d{4})?\\)\\/');
			var r = (input || '').match(re);
			return r ? new Date(((r[1] || '') + r[2]) * 1) : null;
		},
		'CN': function (input, strict) {
			var re = new RegExp('(\\d+)-(\\d+)-(\\d+)');
			var r = (input || '').match(re);
			if (!r) return null;
			return new Date(r[1], (r[2] || 1) - 1, r[3] || 1);
		}
	},
	formatFunctions: {
		"M$": function (timeTick) {
			if (js.IsNull(timeTick) && this.getTime)
				timeTick = this.getTime();
			return '\\/Date(' + timeTick + ')\\/';
		},
		'CN': function (timeTick) {
			var date = new Date(timeTick), y = date.getFullYear(), m = date.getMonth() + 1, d = date.getDate();
			if (m < 10) m = '0' + m;
			if (d < 10) d = '0' + d;
			return y + '-' + m + '-' + d;
		}
	},
	formatJsDateTime: function (d) {
		if (!d || !d.getTime) return '';
		var tick = d.getTime();
		return '\\/Date(' + tick + ')\\/';
	},
	parseDate: function (input, format, strict) {
		var p = Date.parseFunctions;
		if (p[format] == null) {
			Date.createParser(format);
		}
		return p[format](input, true);
	},
	/**
	* 将v转化成date类型
	*/
	parse: function (v) {
		if (!v) return null;
		if (!(v instanceof Date)) {
			if ((!isNaN(v)) && v < 10000000000)
				return new Date(v * 1000);
			var t = null;
			if (v.indexOf('T') >= 0) {
				var sv = v.replace('T', ' ').replace('-','/').replace('-','/');
				var lastComma = sv.lastIndexOf('.');
				if (lastComma >= 0) sv = sv.substr(0, lastComma);
				t = js.Orignal.Date.parse(sv);
				if (!t) t = js.Orignal.Date.parse(v);
			}
			else t = js.Orignal.Date.parse(v);
			if (!isNaN(t)) return new Date(t);
			if (!(t instanceof Date)) t = Date.parseDate(v, 'M$');
			if (!(t instanceof Date)) t = Date.parseDate(v, 'CN');
			v = t;
		}
		return v;
	}
});
js.Apply(Date.prototype, {
	addDays: function (days, includeTime) {
		var me = this;
		var value = Number(me.valueOf());
		value += days * 24 * 3600 * 1000;
		var v = new Date(value);
		if (!includeTime) v = v.clearTime();
		return v;
	}, clearTime: function () {
		return this;
	},
	addMonths: function (months) {
		var me = this;
		var date = new Date(me.getTime());
		date.setDate(1);
		date.setMonth(date.getMonth() + months);
		return date;
	},
	getDateWithoutTime: function (date) {
		date = date || this;
		return new Date(date.getFullYear(), date.getMonth(), date.getDate());
	},
	isLeapYear: function () { var year = this.getFullYear(); if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) return true; return false; },
	getDaysOfYear: function () {
		var days = this.getDate() - 1; var month = this.getMonth();
		for (var i = 0; i < month; i++) days += js.Sys.DaysOfMonth[i];
		var year = this.getFullYear();
		if (month > 1 && (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))) days++;
		return days;
	}, getTotalDays: function () {
		var ticks = this.valueOf();
		return ticks / 1000 / 3600 / 24;
	}, encode: function (o) {
		o = o || this;
		function pad(n) { return js.PadNumber(n, 2); }
		return '' + o.getFullYear() + "-" +
                pad(o.getMonth() + 1) + "-" +
                pad(o.getDate()) + "T" +
                pad(o.getHours()) + ":" +
                pad(o.getMinutes()) + ":" +
                pad(o.getSeconds()) + '';
	}, toUTCDate: function () {
		var me = this;
		return new Date(me.getUTCFullYear(), me.getUTCMonth(), me.getUTCDate(),
				me.getUTCHours(), me.getUTCMinutes(), me.getUTCSeconds(), me.getUTCMilliseconds());
	}, toLocalDate: function () {
		var me = this;
		return new Date(me.getFullYear(), me.getMonth(), me.getDate(),
				me.getHours(), me.getMinutes(), me.getSeconds(), me.getMilliseconds());
	}
});
js.Init = {
	Tooltips: null, IsJQueryFunctionStored: false, _JQueryFunctions: {}, StoreJQueryFunctions: function () { js.Apply(js.Init._JQueryFunctions, jQuery.fn); }, //暂存所有的jQuery方法，因为在EasyUI里会使得一些方法被覆盖掉
	//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
	DisableBackspace: function (e) {
		var ev = e || window.event,//获取event对象     
		obj = ev.target || ev.srcElement,//获取事件源           
		t = obj.type || obj.getAttribute('type'),//获取事件源类型          
		//获取作为判断条件的事件类型  
		vReadOnly = obj.getAttribute('readonly'), vEnabled = obj.getAttribute('enabled');
		//处理null值情况  
		vReadOnly = (vReadOnly == null) ? false : vReadOnly;
		vEnabled = (vEnabled == null) ? true : vEnabled;

		//当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
		//并且readonly属性为true或enabled属性为false的，则退格键失效  
		//var flag1 = (ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea")
		//&& (vReadOnly == true || vEnabled != true)) ? true : false;

		var flag1 = (ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea")
		&& (vReadOnly || !vEnabled)) ? true : false;

		//当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效  
		var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea") ? true : false;

		//判断  
		if (flag2) { return false; } if (flag1) { return false; }
	},
	RestoreJQueryFunctions: function () {
		js.Init.IsJQueryFunctionStored = true;
		for (var name in js.Init._JQueryFunctions)
			jQuery.fn['jQuery_' + name] = js.Init._JQueryFunctions[name];
	},
	//得到当前页面所对应的主框架
	GetMainFrame: function () {
		var parent = window.parent;
		var cwin = window;
		while (cwin.js && !cwin.js.MF && parent != cwin && parent) {
			cwin = parent;
		}
		if (cwin.js && cwin.js.MF) return cwin.js.MF;
		cwin = window;
		parent = window.parent;
		while (cwin.js && cwin.js.EUI && !cwin.js.EUI.MF && parent != cwin && parent) {
			cwin = parent;
		}
		if (!cwin || !cwin.js || !cwin.js.EUI) return null;
		return cwin.js.EUI.MF;
	},
	PageLoaded: function () { },
	InitVariables: function () {
		js.Server = window.Server;
		js.Apply(js.Sys, js.Server);
		js.Init.InitGlobalParams(js.Sys.GlobalParams, null, null, true);
		var htmlTemplates = $('div.template');
		htmlTemplates.each(function (index, item) {
			var jitem = $(item), name = jitem.attr('name'), text = item.innerHTML;
			if (name) js.HtmlTemplate[name] = text;
		});
	},
	_OWidth: null, _OHeight: null,
	_PageSizeHandler: null, _PageSizeHandlers: [], _PageSized: function (isInitPage) {
		var nwidth = $(window).width(), nheight = $(window).height();
		if (Math.abs(js.Init._OWidth - nwidth) <= 2 &&
			Math.abs(js.Init._OHeight - nheight) <= 2) {
			return;
		}
		js.Init._OWidth = nwidth; js.Init._OHeight = nheight;
		//var msg = document.getElementById('system-msg-tip');
		//if (msg) msg.innerHTML += '(' + nwidth + ',' + nheight + ')';
		for (var i = 0; i < js.Init._PageSizeHandlers.length; i++) {
			try {
				js.Init._PageSizeHandlers[i](isInitPage);
			}
			catch (ex) {
			}
		}
	}, RegisterPageSizeHandler: function (handler) {
		if (typeof (handler) != 'function') return;
		for (var i = 0; i < js.Init._PageSizeHandlers.length; i++)
			if (js.Init._PageSizeHandlers[i] == handler) return;
		js.Init._PageSizeHandlers.push(handler);
	},
	//移除__开头的字段，方便上传
	RemoveLocalProperties: function (value, removeDisplayString) {
		if (!value) return value;
		for (var tname in value) {
			var svalue = value[tname];
			if (tname.startsWith('__') || (removeDisplayString && tname.endsWith('_DisplayString'))) {
				delete value[tname];
			}
			else if (js.IsArray(svalue)) {
				for (var i = 0; i < svalue.length; i++)
					js.Init.RemoveLocalProperties(svalue[i], removeDisplayString);
			}
			else if (js.IsObject(svalue) && !js.IsString(svalue) && !js.IsFunction(svalue)) {
				js.Init.RemoveLocalProperties(svalue, removeDisplayString);
			}
		}
		return value;
	},
	/**
	* 初始化全局的参数
	* @maxDeep 最大的深度，默认为5
	*/
	InitGlobalParams: function (params, maxDeep, deep, toLower) {
		if (params == null) return null;
		if (!maxDeep) maxDeep = 5;
		if (!deep) deep = 0;
		if (deep > maxDeep || params.__IsGlobalParamsInited) return params;
		var value;
		for (var key in params) {
			if (key.startsWith('__')) continue;
			value = params[key];
			if (value) {
				if (value.$values && js.IsArray(value.$values)) {
					value = value.$values;
					params[key] = value;
				}
				if (key == 'QueryParams' || key == 'UserData') {
					js.Init.InitGlobalParams(value, maxDeep, deep + 1, false);
					continue;
				}
				if (typeof (value) == 'object' && value.year && value.month && value.timeOfTicks) {
					params[key] = new Date(value.year, value.month, value.date, value.hours, value.minutes, value.seconds, value.ms);
				}
				else if (typeof (value) == 'string' && value.startsWith('/Date')) {
					params[key] = Date.parse(value);
				}
				else if (typeof (value) == 'string' && value.indexOf('T') > 0 && value.length >= 10 &&
					(key.endsWith('_date') || key.endsWith('_rq') || key.endsWith('_sj') || key.endsWith('_time'))
					) {
					params[key] = Date.parse(value);
				}
				else if (js.IsArray(value)) {
					for (var i = 0; i < value.length; i++) {
						if (value[i]) value[i] = js.Init.InitGlobalParams(value[i], maxDeep, deep + 1, toLower);
					}
				}
				else if (typeof (value) == 'object') {
					js.Init.InitGlobalParams(value, maxDeep, deep + 1, toLower);
				}
			}
			if (toLower) params['__' + key.toLowerCase()] = params[key]; //使用最小的key，后续在取数据的时候可以
		}
		params.__IsGlobalParamsInited = true;
		return params;
	},
	ShowRenderTimeCost: function () {
		var now = new Date(), interval = now - js.Sys.ClientTimeStamp;
		var msg = document.getElementById('system-msg-tip');
		if (msg) msg.innerHTML += interval + '(ms), ';
	}
}
js.Math = {
	//整除
	IntDiv: function (a, b) { return Math.floor(a / b); },
	//四舍五入
	Round: function (value, percision) {
		if (isNaN(value) == false && value == 0) return 0;
		if (!value || isNaN(value)) return null;
		return Number(Number(value).toFixed(percision));
	},
	//四舍五入到万
	RoundTo10K: function (value, percision) { return js.Math.Round(value / 10000, percision); },
	//四舍五入到亿
	RoundTo100M: function (value, percision) { return js.Math.Round(value / 100000000, percision); },
	//转化成百分比，如果total为0那么也会达到0，否则达到current*100%/total, 默认精度为2为小数
	ToPercent: function (current, total, percision) {
		if (js.IsNull(percision)) percision = 2;
		total = Number(total);
		if (isNaN(total)) return 0;
		current = Number(current);
		if (isNaN(current)) return 0;
		if (total == 0) return 0;
		var value = (current * 100) / total;
		return Number(value.toFixed(percision));
	},
	//对一个数组进行排序，calFunc是一个函数，输入a, b两个，返回1, 0, -1， 如果a>b则为1, 如果a=b则为0，如果a<b则-1
	Sort: function (array, calFunc, scope) {
		if (!array && !array.length) return array;
		var t = 0, ta, tb;
		for (var i = 0; i < array.length - 1; i++) {
			t = i; ta = array[i];
			if (ta) {
				for (var j = i + 1; j < array.length; j++) {
					tb = array[j];
					if (tb == null) { t = j; break; }
					if ((!calFunc && ta > tb) || (calFunc && calFunc.call(scope || this, ta, tb) > 0)) { t = j; ta = tb; }
				}
				if (t != i) {
					ta = array[i];
					array[i] = array[t];
					array[t] = ta;
				}
			}
		}
		return array;
	},
	//对一个数组进行排序，propName为指定的属性名, isDesc代表正序还是逆序，asc还是desc
	SortArray: function (array, propName, isDesc) {
		return js.Math.Sort(array, function (a, b) {
			var sa = a[propName], sb = b[propName];
			if (isDesc) return js.Math.Compare(sb, sa);
			return js.Math.Compare(sa, sb);
		});
	},
	//两个对象，一般为两个数或者两个字符串的比较，返回1， 0， -1。如果a>b则为1, 如果a=b则为0，如果a<b则-1
	Compare: function (a, b) {
		if (a == null) {
			if (b == null) return 0; else return -1;
		} else if (b == null) return 1;
		else if (a < b) return -1;
		else if (a == b) return 0;
		else return 1;
	},
	//对两个对象的字段进行比较，如果是字段值对象，那么比较具体的内容，如果字段值是数组，那么返回新增、编辑、删除的数据
	GetComparedResult: function (a, b, maxLevel, ingoredFields, cLevel) {
		cLevel = cLevel || 0;
		var result = {};
		if (a && !b) js.CopyObject(a, result, true, null, ingoredFields);
		else if (!a && b) js.CopyObject(b, result, true, null, ingoredFields);
		else {
			for (var tname in a) {
				if (ingoredFields[tname]) continue;
				var ta = a[tname], tb = b[tname];
				if (js.IsFunction(ta)) continue;
				if (ta && !tb) { result[tname] = ta; continue; }
				else if (!ta && tb) { result[tname] = tb; continue; }
				if (js.IsObject(ta)) {
					if (cLevel < maxLevel - 1)
						result[tname] = js.Math.GetComparedResult(ta, tb, maxLevel, ingoredFields, cLevel + 1);
					continue;
				}
				else if (js.IsArray(ta)) {

				}
				else {
					if (ta != tb) result[tname] = tb;
					continue;
				}
			}
		}
	},
	ResolveNameCardNo: function (cardNo) {
		if (cardNo && cardNo.trim) cardNo = cardNo.trim();
		if (cardNo && cardNo.length > 18) {
			//如果不是18位，那么处理一下
			var len = cardNo.length, text = '', ch;
			for (var i = 0; i < len; i++) {
				ch = cardNo.substr(i, 1);
				if (ch >= '0' && ch <= '9') text += ch;
				else if (ch == 'x' || ch == 'X') text += 'X';
				if (text.length >= 18) break;
			}
			cardNo = text;
		}
		return cardNo;
	},
	NameCardArea: { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外" },
	NameCardCheckSum: [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1],
	//是否为有效的身份证号码(中国的)
	IsValidNameCard: function (socialNo, hideErrorMsg) {
		var info = js.Math.GetNameCardInfo(socialNo);
		if (info.success) return true;
		if (!hideErrorMsg && info.msg) {
			js.Web.ShowAlertMsg(info.msg);
		}
		return false;

	},
	//根据身份证信息，获取身份证信息中的相关附加信息，比如性别，籍贯，出身年月, 返回对象为{success:true, msg:'', gendar:true, birthday:Date, provinceCode:'32', provinceName:'江苏'}
	GetNameCardInfo: function (socialNo) {
		var result = { success: false, msg: '输入身份证号码格式不正确!', gendar: true, birthday: null, provinceCode: null, provinceName: null };
		if (!socialNo) { result.msg = '输入身份证号码不能为空!'; return result; }
		var len = socialNo.length, now = new Date();
		if (len != 15 && len != 18) { return result; }
		var area = js.Math.NameCardArea, areaPart = parseInt(socialNo.substr(0, 2)), areaName = area[areaPart], yearPart = null, monthPart = null, dayPart = null;
		if (!areaName) { return result; }
		if (len == 15) { yearPart = '19' + socialNo.substr(6, 2); monthPart = socialNo.substr(8, 2); dayPart = socialNo.substr(10, 2); }
		else { yearPart = socialNo.substr(6, 4); monthPart = socialNo.substr(10, 2); dayPart = socialNo.substr(12, 2); }
		yearPart = parseInt(yearPart); monthPart = parseInt(monthPart); dayPart = parseInt(dayPart);
		if (isNaN(yearPart) || isNaN(monthPart) || isNaN(dayPart)) { return result; }
		if (monthPart == 1 || monthPart == 3 || monthPart == 5 || monthPart == 7 || monthPart == 8 || monthPart == 10 || monthPart == 12) {
			if (dayPart > 31) return result;
		}
		else if (monthPart == 4 || monthPart == 6 || monthPart == 9 || monthPart == 11) {
			if (dayPart > 30) return result;
		}
		else if (monthPart == 2) {
			if ((yearPart % 4 == 0 && yearPart % 100 != 0) || yearPart % 400 == 0) {
				if (dayPart > 29) return result;
			} else {
				if (dayPart > 28) return result;
			}
		}
		else return result;
		try {
			result.birthday = new Date(yearPart, monthPart - 1, dayPart);
		}
		catch (e) { return result; }
		if (result.birthday.valueOf() >= now.valueOf()) return result;
		result.provinceCode = areaPart;
		result.provinceName = areaName;
		var tChar = socialNo.charAt(len - 1);
		if (len == 15) {
			pattern = /^\d{15}$/;
			if (pattern.exec(socialNo) == null) return result;
			if ((tChar - '0') % 2 == 0) result.gendar = false; else result.gendar = true;
			result.success = true; result.msg = null;
			return result;
		}
		if (tChar != 'x' && tChar != 'X') {
			pattern = /^\d{18}$/;
			if (pattern.exec(socialNo) == null) return result;
		}
		var Wi = js.Math.NameCardCheckSum, lSum = 0, nNum = 0, nCheckSum = 0;
		for (i = 0; i < 17; ++i) {
			tChar = socialNo.charAt(i);
			if (tChar < '0' || tChar > '9') return result;
			else { nNum = tChar - '0'; }
			lSum += nNum * Wi[i];
		}
		tChar = socialNo.charAt(len - 1);
		if (tChar == 'X' || tChar == 'x') { lSum += 10 * Wi[17]; }
		else if (tChar < '0' || tChar > '9') return result;
		else { lSum += (socialNo.charAt(17) - '0') * Wi[17]; }
		if ((lSum % 11) == 1) {
			tChar = socialNo.charAt(len - 2);
			if ((tChar - '0') % 2 == 0) result.gendar = false; else result.gendar = true;
			result.success = true; result.msg = null; return result;
		}
		else return result;
	}
}

js.UI = {
	_Trace: {}, _LoadingMaskHandler: null, ShowLoadingMask: function (msg, timeout) {
		msg = msg || '请稍候...';
		var mask = js.UI._LoadingMaskHandler;
		if (!mask && window.Ext) {
			mask = new window.Ext.LoadMask({ target: document.body, msg: msg });
			js.UI._LoadingMaskHandler = mask;
		}
		if (!mask) return;
		if (mask.update) mask.update(msg);
		if (mask.show) mask.show();
		if (timeout > 0) {
			window.setTimeout(function () {
				js.UI.HideLoadingMask();
			}, timeout);
		}
	}, HideLoadingMask: function () {
		var mask = js.UI._LoadingMaskHandler;
		if (!mask) return;
		if (mask.hide) mask.hide();
	},
	ShowTraceUIText: function () {
		var text = '';
		for (var name in js.UI._Trace)
			text += name + ', ';
		js.Web.ShowMsg(text);
	},
	Resources: [],
	/**
	* 注册一个资源
	**/
	RegisterResource: function (r) {
		if (r) {
			function adjustResource(ro) {
				for (var sname in ro) {
					var nname = sname.toLocaleUpperCase();
					if (nname != sname) {
						var svalue = ro[sname];
						ro[nname] = svalue;
						if (typeof (svalue) == 'object') {
							adjustResource(svalue);
						}
					}
				}
			}
			adjustResource(r);
			this.Resources.push(r);
		}
		return r;
	},
	//转化成经过调整的html文本信息
	ToAdjustedHtmlText: function (otext) {
		if (!otext) return otext;
		var text = otext.toString(), len = text.length;
	},
	/**
	* 创建常用的文字，适合于button上的文字
	* @text 文本信息，如果已经处理过了，那么不再处理
	* @size 文字大小
	**/
	GenerateSpanText: function (text, size, color, required, title, width) {
		if (js.IsNull(text)) return '';
		if (text && text.startsWith && text.startsWith('<span style=')) return text;
		var style = '';
		title = title || text || '';
		if (required) {
			if (text.endsWith('<span style="color:red;">*</span>') == false)
				text = text + '<span style="color:red;">*</span>';
		}
		else if (color == null) color = 'black';
		if (color) style += 'color: ' + color + ';';
		if (size != null && size > 0) style += 'font-size : ' + size + 'px;';
		if (width > 0) style += 'width : ' + width + 'px;';
		//return String.format('<span style="{0}">{1}</span>', style, text);
		return '<span style="' + style + '" title="' + title + '">' + text + '</span>';
	},
	GenerateDefaultIconClass: function (id, iconClass) {
		if (id) return 'icon-baseinfo icon-' + id.toLowerCase();
		if (iconClass) iconClass; //此处的代码目前还没有找到合适的
		return 'icon-baseinfo icon-action';
	},
	//支持语言版本的函数，format实际上是文本资源的标识
	UIText2: function (format, container, returnNullIfNotFound) {
		return js.UI.UIText(format, null, returnNullIfNotFound, container);
	},
	UIText: function (format, className, returnNullIfNotFound, container) {
		if (!format) return format;
		var text = null, len = format.length, i = 0, resource, tformat = 'INFO_' + format;
		if (len > 5) {
			if (format.endsWith('_text')) tformat = format.substr(0, len - 5);
			else if (len > 6 && format.endsWith('_range')) tformat = format.substr(0, len - 6);
		}
		container = container || this;
		className = className || container.className;
		if (className) {

		}
		className = className || 'VGResource';
		if (className) {
			for (i = 0; i < js.UI.Resources.length; i++) {
				resource = js.UI.Resources[i];
				if (resource[className] && resource[className][format])
					return resource[className][format];
				if (className != 'VGResource' && resource['VGResource'] && resource['VGResource'][format])
					return resource['VGResource'][format];
				if (resource[className] && resource[className][tformat])
					return resource[className][tformat];
				if (className != 'VGResource' && resource['VGResource'] && resource['VGResource'][tformat])
					return resource['VGResource'][tformat];
			}
		}
		for (i = js.UI.Resources.length - 1; i >= 0; i--) {
			resource = js.UI.Resources[i];
			if (resource[format]) return resource[format];
		}
		if (js.Sys.Debug) {
			var topWindow = js.Web.FindWindow(window, null);
			if(typeof topWindow.js !='undefined'&&topWindow.js)
				topWindow.js.UI._Trace[format] = format;
		}
		if (returnNullIfNotFound) return null;
		return format;
	},
	//截断一个字符串
	TruncateString: function (text, len, maskText) {
		if (!text) return text;
		maskText = maskText || '...';
		text = text.toString();
		if (text.length < len) return text;
		return text.substr(0, len) + maskText;
	},
	//对数据进行筛选
	FilterDataRow: function (row, queryText, pinyinQueryText, textField, valueField, matchAll) {
		if (!row) return false; if (!queryText) return true;
		var text = null, value = null, nq = queryText.toUpperCase(),
			ntext = row.__UpperText,
			pinyinText = row.__PinyinText;
		if (textField) text = row[textField];
		if (valueField) value = row[valueField];
		if (queryText == value) return true;
		if (!text) return false;
		if (!ntext) {
			ntext = text.toUpperCase();
			row.__UpperText = ntext;
		} else ntext = text.toUpperCase();
		if (matchAll && nq != ntext) return false;
		if (!pinyinText && js.UI.PinYin) {
			pinyinText = js.UI.PinYin.CC2PY(ntext, false).toUpperCase();
			row.__PinyinText = pinyinText;
		}
		if (!pinyinQueryText) pinyinQueryText = nq;
		if (ntext.indexOf(nq) >= 0) return true;
		if (pinyinText && pinyinQueryText)
			return pinyinText.indexOf(pinyinQueryText) >= 0;
		return false;
	}
}
js.UI.GenerateButtonText = js.UI.GenerateSpanText;
//if ($.fn.datagrid) //Check if EasyUI using
//	js.Editor = $.fn.datagrid.defaults.editors;
//else js.Editor = {};

//js.Apply(js.Editor, { //Add your extension here
//});
js.VTypes = {
	mobile: function (v) { return js.IsMobilePhone(v); },
	mobileText: '不是有效的手机号码',
	email: function (v) { return js.IsEmail(v); },
	emailText: '不是有效的邮箱信息',
	IPAddress: /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/,
	IPAddressText: '必须是有效的IP地址',
	IPAddressMask: /[\d\.]/i,
	NameCard: function (v, field) {
		v = js.Math.ResolveNameCardNo(v);
		if (v) return js.Math.IsValidNameCard(v, true);
		return false;
	},
	NameCardText: '不是有效的身份证号码',
	NameCardMask: /[\d]*[\d\X]/i
};
js.Editor = {
	text: function (configs, required, container) {
		configs = configs || {};
		configs.container = container;
		if (js.IsNull(required) == false) configs.required = required;
		return { type: 'text', options: configs, container: container };
	},
	checkbox: function (configs, required, container) {
		configs = configs || {};
		configs.container = container;
		if (js.IsNull(required) == false) configs.required = required;
		return { type: 'checkbox', options: configs, container: container };
	},
	combobox: function (configs, required, textField, valueField, urlOrData, relatedTextColumn, container) {
		configs = configs || {};
		configs.required = required;
		configs.textField = textField || 'DisplayString';
		configs.valueField = valueField || 'Key';
		configs.relatedTextColumn = relatedTextColumn;
		configs.container = container;
		if (typeof (urlOrData) == 'string') configs.url = urlOrData;
		else configs.data = urlOrData;
		return { type: 'combobox', options: configs, container: container };
	},
	combotree: function (configs, required, container) {
		configs = configs || {};
		configs.container = container;
		if (js.IsNull(required) == false) configs.required = required;
		return { type: 'combotree', options: configs, container: container };
	},
	datebox: function (configs, required, container) {
		configs = configs || {};
		configs.container = container;
		if (js.IsNull(required) == false) configs.required = required;
		return { type: 'datebox', options: configs, container: container };
	},
	numberbox: function (configs, required, precision, container) {
		configs = configs || {};
		configs.container = container;
		if (js.IsNull(required) == false) configs.required = required;
		if (js.IsNull(precision) == false) configs.precision = precision;
		return { type: 'numberbox', options: configs, container: container };
	},
	textarea: function (configs, required, container) {
		configs = configs || {};
		configs.container = container;
		if (js.IsNull(required) == false) configs.required = required;
		return { type: 'textarea', options: configs, container: container };
	},
	validatebox: function (configs, required, container) {
		configs = configs || {};
		configs.container = container;
		if (js.IsNull(required) == false) configs.required = required;
		return { type: 'validatebox', options: configs, container: container };
	}
};
//一些常用的renderer，适合于easyui和extjs
js.Renderer = {
	//用于呈现多个renderer的基础renderer，它会取renderers这个字段的值
	MultiRenderer: function (value, meta, record, rowIndex, colIndex, store, scope) {
		var column = meta.column || meta, renderers = column.renderers, name = column.name || column.dataIndex, target_name = name + '_DisplayString', data = record ? (record.data || record) : value;
		if (!renderers) return value;
		if (!js.IsArray(renderers)) renderers = [renderers];
		for (var i = 0; i < renderers.length; i++)
			value = renderers[i].call(scope || this, value, meta, record, rowIndex, colIndex, store, scope);
		return value;
	},
	//使用模板的呈现
	TemplateRenderer: function (value, meta, record, rowIndex, colIndex, store, scope) {
		var column = meta.column || meta, name = column.name || column.dataIndex,
			templateName = column.template || column.category || name;
		if (record) {
			return js.FormatObjectWithTemplate(templateName, record.data || record, false);
		}
		return value;
	},
	//显示成缓存中的字段
	CacheRenderer: function (value, meta, record, rowIndex, colIndex, store, scope) {
		var column = meta.column || meta, name = column.name || column.dataIndex, target_name = name + '_DisplayString', data = record ? (record.data || record) : value;
		if (!data) return value;
		return data[target_name] || value;
	},
	//外键字段，没有缓存的字段
	ForeignKeyRenderer: function (value, meta, record, rowIndex, colIndex, store, scope) {
		var column = meta.column || meta, name = column.name || column.dataIndex, target_name = name, data = record ? (record.data || record) : value;
		if (!data || !name) return value;
		var tdata = data[target_name + '_DisplayString']; if (tdata) return tdata;
		tdata = data[target_name + '_MC']; if (tdata) return tdata;
		tdata = data[target_name + '_name']; if (tdata) return tdata;
		tdata = data[target_name + '_NAME']; if (tdata) return tdata;

		var tindex = name.lastIndexOf('_');
		if (tindex < 0) return value;
		target_name = name.substr(0, tindex);
		tdata = data[target_name + '_DisplayString']; if (tdata) return tdata;
		tdata = data[target_name + '_MC']; if (tdata) return tdata;
		tdata = data[target_name + '_name']; if (tdata) return tdata;
		tdata = data[target_name + '_NAME']; if (tdata) return tdata;
		return value;
	},
	//如果字段存在值，那么显示预定义的displayText或者是显示列头，如果没有值就为空
	HeaderRenderer: function (value, meta, record, rowIndex, colIndex, store, scope) {
		var column = meta.column || meta, name = column.name || column.dataIndex;
		if (value)
			return column.displayText || column.text;
		return null;
	},
	//用于显示性别的，男女
	GendarRenderer: function (value, meta, record, rowIndex, colIndex, store, scope) {
		var column = meta ? (meta.column || meta) : null, name = column ? (column.name || column.dataIndex) : null, target_name = name ? (name + '_DisplayString') : null, data = record ? (record.data || record) : value;
		if (!data) return '女';
		if ((target_name && data[target_name]) || value) return '男';
		else return '女';
	},
	//额外的转化
	FormaterConverter: function (value, record, rowIndex) {
		if (this.renderer) return this.renderer(value, this, record, rowIndex, this.colIndex, null, this.container);
		return value;
	},
	EncryptRenderer: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (!value) return value;
		if (js.Md5) return js.Md5(value).toUpperCase();
		return value;
	},
	//显示超连接
	HrefRenderer: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (!value) return null;
		scope = scope || this; record = record ? (record.data || record) : {}; meta = meta || {};
		var column = meta.column || meta;
		var urlField = column.urlField, tooltip = column.tooltip || value, ttooltip = record[tooltip] || record.tooltip || tooltip, url = record[urlField] || '#';
		return String.format('<a class="renderer-href"  href="{0}" style="text-decoration:underline" title="{1}">{2}</a>', url, ttooltip, value);
	},
	//显示成连接接口, 如果是grid，那么可以传递一个url参数，这个url可以是function，也可以是一个字符串，字符串接受value和rowIndex两个参数
	ActionLinkRenderer: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (!value) return null;
		scope = scope || this; record = record || {};
		var column = meta.column || meta, name = column.name || column.dataIndex,
			text = column.displayText || column.text, url = column.url, tooltip = column.tooltip || value,
			ttooltip = record.tooltip || tooltip, target = column.target || '_self';
		if (js.IsFunction(url)) {
			url = url(value, meta, record, rowIndex, colIndex, store, scope);
		}
		else url = String.format(url, value, rowIndex);
		return String.format('<a class="renderer-href renderer-action-link" href="{0}" target="{3}" style="text-decoration:underline" title="{1}">{2}</a>', url, ttooltip, text, target);
	},
	GridButtonRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		scope = scope || this;
		var displayText = null, tcolumn = meta.column || meta;
		if (tcolumn && tcolumn.displayText && typeof (tcolumn.displayText) == 'function') {
			displayText = tcolumn.displayText(value, meta, record, rowIndex, colIndex, store, scope);
			if (!displayText) return null;
		}
		else displayText = tcolumn.displayText || value;
		var tid = (tcolumn._container ? tcolumn._container.id : null) || scope.el.id || scope.el.attr('id');
		if (window.Ext)
			return '<span class="ext-grid-button">' + displayText + '</span>';
		//return String.format('<a href="#" onclick="js.Controls.EUIContentClicked(\'{0}\', \'{1}\',[{2},{3}])">{4}</a>', scope.el.attr('id'), '_CellClicked', rowIndex, colIndex, displayText);
		return '<a  class="renderer-grid-button"  href="#" onclick="js.Controls.EUIContentClicked(\'' + tid + '\', \'' + '_CellClicked' + '\',[' + rowIndex + ',' + colIndex + '])">' + displayText + '</a>';
	},
	//显示成button
	ButtonRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		var displayText = null;
		if (meta) meta = meta.column || meta;//extjs下需要取meta.column
		if (meta && meta.displayText && typeof (meta.displayText) == 'function') {
			displayText = meta.displayText(value, meta, record, rowIndex, colIndex, store, scope);
			if (!displayText) return null;
		}
		else displayText = meta.displayText || value;
		var url = meta.url;
		if (typeof (url) == 'function') {
			url = url(value, meta, record, rowIndex, colIndex, store, scope);
		}
		else if (url) {
			url = js.Web.AppendUrlParams(url, record);
		}
		url = js.Web.AppendUrlCommonVars(url);
		var target = meta.target || '_self';
		if (displayText && url) {
			//return '<a href="/SystemAdmin/LossPercents_Limits?GAME_ID=' + record.GAME_ID + '&ITEM_ID=' + record.ITEM_ID + '&LEVEL_CODE=' + meta.name + '" class="easyui-linkbutton" alt=""><SPAN class=l-btn-left><SPAN class="l-btn-text">' + displayText + '</SPAN></SPAN></a>';
			return '<a href="' + url + '" class="eui-button eui-button_75 renderer-button" alt="" target="' + target + '"><SPAN class="l-btn-left"><SPAN class="l-btn-text">' + displayText + '</SPAN></SPAN></a>';
		}
		return null;
	},
	//展示成周几的render
	DaysInWeekRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (value === 0 || !isNaN(value)) {
			value = Math.round(value);
			if (value < 0 || value > 6) value = 0;
			if (value == 0) return '周日';
			if (value == 1) return '周一';
			if (value == 2) return '周二';
			if (value == 3) return '周三';
			if (value == 4) return '周四';
			if (value == 5) return '周五';
			if (value == 6) return '周六';
		}
		return value;
	},
	FileSizeRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (value === 0 || !isNaN(value)) {
			value = Number(value);
			if (value < 1024) return value + 'B';
			else if (value < 1024 * 1024) return js.Math.Round(value / 1024, 1) + 'K';
			else if (value < 1024 * 1024 * 1024) return js.Math.Round(value / 1024 / 1024, 2) + 'M';
			else return js.Math.Round(value / 1024 / 1024 / 1024, 2) + 'G';
		}
		return value;
	},
	//显示一个友好的日期信息
	FriendlyDateTimeRender: function (targetDate, meta, record, rowIndex, colIndex, store, scope) {
		if (targetDate == null) return null;
		var targetDate = Date.parse(targetDate), now = new Date(), interval = now.valueOf() - targetDate.valueOf();
		var seconds = js.Math.IntDiv(interval, 1000);
		if (seconds < 0) {
			if (seconds > -3) return "马上";
			if (seconds > -60) return seconds + "秒后";
			var minutes = js.Math.IntDiv(seconds, 60);
			if (minutes > -60) return minutes + "分钟后";
			var hours = js.Math.IntDiv(minutes, 60);
			if (hours > -24) return hours + "小时后";
			if (!includeTimeIfOverDays)
				return js.Math.IntDiv(hours, 24) + "天后";
		}
		else {
			if (interval.TotalSeconds < 3) return "刚刚";
			if (seconds < 60) return seconds + "秒前";
			var minutes = js.Math.IntDiv(seconds, 60);
			if (minutes < 60) return minutes + "分钟前";
			var hours = js.Math.IntDiv(minutes, 60);
			if (hours < 24) return hours + "小时前";
			if (!includeTimeIfOverDays)
				return js.Math.IntDiv(hours, 24) + "天前";
		}
		return js.Renderer.DateTimeRender(value, meta, record, rowIndex, colIndex, store, scope);
	},
	TimeSizeRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (value) {
			var t = null;
			if (!isNaN(value)) {
				if (value < 1000) return value + '毫秒';
				else if (value < 60000) return js.Math.Round(value / 1000, 1) + '秒';
				value = value / 1000;
				if (value < 3600) return js.Math.Round(value / 60, 0) + '分' + js.Math.Round(value % 60, 0) + '秒';
				value = value / 60;
				if (value < 24 * 60) return js.Math.Round(value / 60, 0) + '小时' + js.Math.Round(value % 60, 0) + '分';
				t = new Date(0, 0, 0, 0, 0, 0, value);
			}
			else t = Date.parse(value);
			if (t) {
				if (t.getDay() > 0)
					return '第' + (t.getDay() + 1) + '天 ' + t.format(js.Sys.TimeSpanFormatString);
				return t.format(js.Sys.TimeSpanFormatString);
			}
		}
		return value;
	},
	//显示时间类型的列
	TimeSpanRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (value) {
			var t = null;
			if (!isNaN(value)) {
				t = new Date(0, 0, 0, 0, value);
			}
			else t = Date.parse(value);
			if (t) {
				if (t.getDay() > 0)
					return '第' + (t.getDay() + 1) + '天 ' + t.format(js.Sys.TimeSpanFormatString);
				return t.format(js.Sys.TimeSpanFormatString);
			}
		}
		return value;
	},
	//bool类型的列， 如果为true，那么显示，否则为空白
	BooleanRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		meta = meta || {};
		var trueString = meta.trueString === null || meta.trueString === undefined ? js.UI.UIText('Info_True') : meta.trueString;
		var falseString = meta.falseString === null || meta.falseString === undefined ? null : meta.falseString;
		if (value && value != 0) return trueString;
		else return falseString;
	},
	//允许为空的bool类型的列，如果为null,则显示为空白
	NullableBooleanRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		var trueString = meta.trueString === null || meta.trueString === undefined ? js.UI.UIText('Info_True') : meta.trueString;
		var falseString = meta.falseString === null || meta.falseString === undefined ? js.UI.UIText('Info_False') : meta.falseString;
		if (value && value != 0) return trueString;
		else if (value !== null) return falseString;
		return null;
	},
	//显示日期类型的列，包含时间信息
	DateTimeRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (value) {
			var t = null;
			if (!isNaN(value)) {
				if (value < 10000000000) value = value * 1000;
				t = new Date(value);
			}
			else t = Date.parse(value);
			if (t)
				if (t.format) {
					var text = null;
					if ($.Metro) text = t.format('yyyy-mm-dd HH:MM:ss');
					else text = t.format(js.Sys.DateTimeFormatString);
					text = text.replace(' 00:00:00', '');//如果发现本身时间就是日期格式就不必要显示最后的00:00:00
					return text;
				}
				else js.Web.ShowAlertMsg('你加载的js文件有问题');
		}
		return value;
	},
	//显示日期信息的列，只显示日期字段
	DateRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (value) {
			var t = Date.parse(value);
			if (t) if (t.format) {
				if ($.Metro) return t.format('yyyy-mm-dd');
				return t.format(js.Sys.DateFormatString);
			}
			else js.Web.ShowAlertMsg('你加载的js文件有问题');
		}
		return value;
	},
	//根据生日显示成年龄
	AgeRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (value) {
			var t = Date.parse(value);
			if (t) {
				var now = new Date();
				return now.getYear() - t.getYear();
			}
		}
		return value;
	},
	ValueNotNullRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (!value) { value = 0; }
		return value;
	},
	//显示钱的render，一般钱精确到小数点后两位，并且右对齐
	MoneyRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (!value) return value; if (isNaN(value)) return js.UI.GenerateButtonText('*', null, red) + value;
		return js.Math.Round(value, 2);
	},
	//显示万元的render，在中文中常见
	MoneyToWRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (!value) return value; if (isNaN(value)) return js.UI.GenerateButtonText('*', null, red) + value;
		return js.Math.RoundTo10K(value, 2);
	},
	//将数字变成百分比的render, 会在后面加一个'%'
	ToPercentRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (!value) return value; if (isNaN(value)) return js.UI.GenerateButtonText('*', null, red) + value;
		return js.Math.Round(value * 100, 0) + ' %';
	},
	//常见的显示成数字的render，一般数字列是右对齐的
	NumberRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (!value) return value; if (isNaN(value)) return js.UI.GenerateButtonText('*', null, red) + value;
		return value;
	},
	ChineseDigitsMap: ["零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"],
	ChineseRadicesMap: ['', "拾", "佰", "仟"],
	ChineseBigRadicesMap: ['', "万", "亿", '万亿', '万万亿'],
	ChineseDecimalsMap: ["角", "分"],
	ChineseMaxNumber: 9999999999999999999.99,
	//将数字转化成中国汉字的数字
	NumberToChineseRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		scope = scope || this;
		if (!js.IsString(value)) value = value.toString();
		var currencyDigits = value, MAXIMUM_NUMBER = js.Renderer.ChineseMaxNumber, CN_SYMBOL = "人民币", CN_DOLLAR = "元", CN_INTEGER = "整", CN_ZERO = "零",
			integral, decimal, outputCharacters = '', parts, digits, radices, bigRadices, decimals, zeroCount, i, p, d, quotient, modulus;
		currencyDigits = currencyDigits.replace(/,/g, "");
		currencyDigits = currencyDigits.replace(/^0+/, "");
		if (Number(currencyDigits) > MAXIMUM_NUMBER) { js.Web.ShowMsg("金额超过转换要求的最大值!"); return "金额超过转换要求的最大值!"; }
		parts = currencyDigits.split(".");
		digits = js.Renderer.ChineseDigitsMap;
		radices = js.Renderer.ChineseRadicesMap;
		bigRadices = js.Renderer.ChineseBigRadicesMap;
		decimals = js.Renderer.ChineseDecimalsMap;
		if (parts.length > 1) {
			integral = parts[0]; decimal = parts[1] + '00'; decimal = decimal.substr(0, 2);
		}
		else { integral = parts[0]; decimal = ""; }
		if (Number(integral) > 0) {
			zeroCount = 0;
			for (i = 0; i < integral.length; i++) {
				p = integral.length - i - 1;
				d = integral.substr(i, 1);
				quotient = p / 4;
				modulus = p % 4;
				if (d == "0") { zeroCount++; }
				else {
					if (zeroCount > 0) { outputCharacters += digits[0]; }
					zeroCount = 0;
					outputCharacters += digits[Number(d)] + radices[modulus];
				}
				if (modulus == 0 && zeroCount < 4) {
					outputCharacters += bigRadices[quotient];
				}
			}
			outputCharacters += CN_DOLLAR;
		}
		if (decimal != "") {
			for (i = 0; i < decimal.length; i++) {
				d = decimal.substr(i, 1);
				if (d != "0") { outputCharacters += digits[Number(d)] + decimals[i]; }
			}
		}
		if (outputCharacters == "") { outputCharacters = CN_ZERO + CN_DOLLAR; }
		if (decimal == "" || decimal == "0" || decimal == "00") { outputCharacters += CN_INTEGER; }
		return outputCharacters;
	},
	//一个显示附件的列
	AttachmentRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (!value) return null;
		scope = scope || this; record = record || {};
		var column = meta.column || meta, name = column.name || column.dataIndex,
			text = column.displayText || column.text, tooltip = column.tooltip,
			ttooltip = record.tooltip || tooltip || ('点击查看' + text), target = column.target || '_blank',
			url = js.Web.GenerateUrl('', 'PreviewFile', value);
		return String.format('<a class="renderer-href attachment" href="{0}" target="{3}" style="text-decoration:underline" title="{1}">{2}</a>', url, ttooltip, text, target);
	},
	//一个用于显示ajax view的提示内容，会在文字的后面加一个小图标，然后点击小图标会弹出ajax的提示
	AjaxTooltipRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		if (!value) return null;
		scope = scope || this; record = record || {}; record = record.data || record;
		var column = meta.column || meta, name = column.name || column.dataIndex, key = column.key || name || 'Key', target_name = name + '_DisplayString',
			text = record[target_name] || value || column.displayText || column.text, url = column.url, tooltip = column.tooltip,
			ttooltip = record.tooltip || tooltip, target = column.target,
			dataWidth = column.dataWidth, dataHeight = column.dataHeight, dataTitle = column.dataTitle;
		if (js.IsFunction(url)) {
			url = url.call(scope || this, value, meta, record, rowIndex, colIndex, store, scope);
		}
		else if (url) {
			var tvalue = record.get ? record.get(key) : record[key];
			if (tvalue) url = String.format(url, tvalue, rowIndex);
			else if (url.indexOf('{0}') >= 0) url = null;//字段不存在，但是url又需要的话那么就不要了。
		}
		if (!url && !ttooltip) return text;
		ttooltip = ttooltip || '点击查看详细提示';
		//if (url&&target && target != '_self') return String.format('{0}<a class="renderer-href renderer-help-link icon icon-help" href="{1}" title="{2}" target="{3}"></span>', text, url, ttooltip, target);
		//return String.format('{0}<span class="renderer-href renderer-help-link icon icon-help" url="{1}" title="{2}" data-width="{3}" data-height="{4}" data-title="{5}" onclick="javascript:js.Web._AjaxTooltipRenderClicked.call(this)"></span>', text, url, ttooltip, dataWidth, dataHeight, dataTitle);
		return text + js.Web.CreateTooltipIcon(url, ttooltip, dataWidth, dataHeight, dataTitle, target, column.configs);
	},
	//用户车联网项目颜色名称渲染
	PlateColorRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
		switch (value) {
			case 1:
				value = '蓝色';
				break;
			case 2:
				value = '黄色';
				break;
			case 3:
				value = '黑色';
				break;
			case 4:
				value = '白色';
				break;
			case 5:
				value = '绿色';
				break;
			default:
				value = '其他';
				break;
		}
		return value;
	},
	//用户车联网项目颜色名称渲染
	AlarmTypeRender: function (value, meta, record, rowIndex, colIndex, store, scope) {
	    switch (value) {
	        case 1:
	            value = '紧急报警'
	            break;
	        case 2:
	            value = '超速报警';
	            break;
	        case 3:
	            value = '疲劳驾驶';
	            break;
	        case 4:
	            value = '危险预警';
	            break;
	        case 5:
	            value = 'GNSS模块发生故障';
	            break;
	        case 6:
	            value = 'GNSS天线未接或被剪断';
	            break;
	        case 7:
	            value = 'GNSS天线短路';
	            break;
	        case 8:
	            value = '终端主电源欠压//SF低电压';
	            break;
	        case 9:
	            value = '终端主电源掉电';
	            break;
	        case 10:
	            value = '终端LCD或显示器故障';
	            break;
	        case 11:
	            value = 'TTS模块故障';
	            break;
	        case 12:
	            value = '摄像头故障';
	            break;
	        case 13:
	            value = '道路运输证IC卡模块故障';
	            break;
	        case 14:
	            value = '超速预警';
	            break;
	        case 15:
	            value = '疲劳驾驶预警';
	            break;
	        case 16:
	            value = '抛洒报警';
	            break;
	        case 17:
	            value = '不在指定区域卸料报警';
	            break;
	        case 18:
	            value = '非指定区域行驶报警';
	            break;
	        case 19:
	            value = '当天累计驾驶超时';
	            break;
	        case 20:
	            value = '超时停车//SF超时停留';
	            break;
	        case 21:
	            value = '进出区域';
	            break;
	        case 22:
	            value = '进出路线';
	            break;
	        case 23:
	            value = '路段行驶时间不足/过长';
	            break;
	        case 24:
	            value = '路线偏离报警';
	            break;
	        case 25:
	            value = '车辆VSS故障';
	            break;
	        case 26:
	            value = '车辆油量异常';
	            break;
	        case 27:
	            value = '车辆被盗';
	            break;
	        case 28:
	            value = '车辆非法点火';
	            break;
	        case 29:
	            value = '车辆非法位移';
	            break;
	        case 30:
	            value = '碰撞预警';
	            break;
	        case 31:
	            value = '侧翻预警';
	            break;
	        case 32:
	            value = '非法开门报警';
	            break;
	        case 180:
	            value = '前碰撞预警';
	            break;
	        case 181:
	            value = '车道偏移预警';
	            break;
	        case 182:
	            value = '车距过近预警';
	            break;
	        case 183:
	            value = '未定义报警';
	            break;
	        case 184:
	            value = '行人碰撞报警';
	            break;
	        case 185:
	            value = '频繁变道报警';
	            break;
	        case 186:
	            value = '疲劳驾驶报警';
	            break;
	        case 187:
	            value = '接打电话报警';
	            break;
	        case 188:
	            value = '抽烟报警';
	            break;
	        case 189:
	            value = '视线脱离路面报警';
	            break;
	        case 190:
	            value = '驾驶员异常报警';
	            break;
	        case 191:
	            value = '轮胎异常报警';
	            break;
	        case 192:
	            value = '急刹车';
	            break;
	        case 193:
	            value = '急转弯';
	            break;
	        case 194:
	            value = '异常开门';
	            break;
	        case 195:
	            value = '怠速停车';
	            break;
	        case 196:
	            value = '低挡高速';
	            break;
	        case 197:
	            value = '空档滑行';
	            break;
	        case 198:
	            value = '脱岗预警';
	            break;
	        case 199:
	            value = '左右压线';
	            break;
	        case 200:
	            value = '遮挡摄像头';
	            break;
	        default:
	            value = '未知类型';
	            break;
	    }
	    return value;
	}
}
//与按键相关的
js.Keys = {
	//存储一些特别的key
	SpeicalKeys: { BACKSPACE: 'BACKSPACE', DELETE: 'DELETE', TAB: 'TAB', ENTER: 'ENTER' },
	GeneralKeyDown: function (event) {
		var sender = this;
		var dataOptions = $(sender).data('data-options');
		if (!dataOptions) return;
	},
	//只允许输入整型
	FilterAsIntFields: function (sender, event, limit) {
		sender = sender || this;
		var pvalue = $(sender).val();
		var re = /\d+$/i;
		var cc = js.Keys.FilterKeys(event, re, pvalue, limit);
	},
	//只允许输入浮点类型
	FilterAsFloatFields: function (sender, event, limit, percision) {
		sender = sender || this;
		var pvalue = $(sender).val();
		var re = /^([1-9]\d*|0)(\.)?([0-9])?$/;
		var cc = js.Keys.FilterKeys(event, re, pvalue, limit);
	},
	FilterKeys: function (e, maskRe, previousValue, maxValue, maxLength) {
		if (e.ctrlKey) { return false; }
		var k = e.key;
		var cc = k;
		if (k != null) {
			k = k.toUpperCase();
			//if ((e.isNavKeyPress() || k == js.Keys.SpeicalKeys.BACKSPACE || (k == js.Keys.SpeicalKeys.DELETE && e.button == -1))) {
			if (k == js.Keys.SpeicalKeys.BACKSPACE || k == js.Keys.SpeicalKeys.TAB ||
				(k == js.Keys.SpeicalKeys.DELETE && e.button == -1)) {
				return false;
			}
			var vregx = /[0-9a-zA-Z.]/i;
			if (vregx.test(k)) cc = k;
			else cc = String.fromCharCode(e.which || e.charCode || e.keyCode);
		}
		else {
			k = e.which || e.charCode || e.keyCode;
			if (k == 8 || k == 9 || (k == 46 && e.button == -1)) return false;
			cc = String.fromCharCode(k);
		}
		if (!js.IsGecko && !cc) { return false; }
		if (previousValue) cc = previousValue + cc;
		if (maskRe && !maskRe.test(cc)) { js.StopEvent(e); return false; }
		if (!js.IsNull(maxValue) && (isNaN(cc) || Number(cc) > maxValue)) { js.StopEvent(e); return false; }
		if (!js.IsNull(maxLength) && cc.length > maxLength) { js.StopEvent(e); return false; }
		return cc;
	}
}
js.HashQuery = {
	//从url中获取相应的参数信息
	getPageState: function () {
		if (!location.hash || location.hash.length == 1) return;
		try {
			var hash = decodeURIComponent(location.hash), params = null;
			if (!hash) return null;
			if (hash.charAt('0') == '#') hash = hash.substring(1);
			params = eval('(' + hash + ')');
			return params;
		}
		catch (error) {

		}
		return null;
	},
	//把当前的参数序列化到url中
	savePageState: function (pageState) {
		var str = js.ToJsonString(pageState, true),
			hash = encodeURIComponent(str),
			newUrl = location.toString(),
			index = newUrl.indexOf('#');
		//location.hash = hash;
		if (index >= 0) newUrl = newUrl.substr(0, index) + '#' + hash;
		else newUrl = newUrl + '#' + hash;
		location.replace(newUrl);
		return newUrl;
	}
};
js.Controls = {
	CreateControlItems: function () {
		var args = arguments[0], items = [];
		if (args == null || args.length <= 0) return items;
		else if (args.length <= 1) return args[0];
		for (var t = 0; t < args.length; t++) {
			var arg = args[t];
			if (arg) {
				if (js.IsArray(arg)) {
					for (var i = 0; i < arg.length; i++) items.push(arg[i]);
				}
				else items.push(arg);
			}
		}
		return items;
	},
	//获取combobox的key对应值
	GetSelectedItem: function (arr, key) {
		var result = null;
		if (arr == null)
			return null;
		for (var i = 0; i < arr.length; i++) {
			var item = arr[i];
			if (item.Key == key) {
				result = item;
				break;
			}
		}
		return result;
	},
	//验证是否包含不正确的输入，注意这里只是一个占位，实际实现在jquery.easyui.shared.js中
	//通过各种途径获取一个field的name
	GetName: function (field) {
		if (!field) return null;
		if (field.getName) return field.getName();
		if (field.GetName) return field.GetName();
		if (field.name) return field.name;
		if (field.length && field.attr) return field.attr('name');
		if (js.IsString(field)) return $(field).attr('name');
		if (js.IsObject(field)) return $(field).attr('name');
		return null;
	},
	//通过各种途径获取一个field的value
	GetValue: function (field) {
		if (!field) return null;
		if (field.getRealValue) return field.getRealValue();
		if (field.getValue) return field.getValue();
		if (field.GetValue) return field.GetValue();
		if (field.length && field.val) return field.val();
		if (field.length && field.attr) return field.attr('value');
		if (js.IsString(field)) return $(field).val();
		if (js.IsObject(field)) return $(field).val();
		return field.value;
	},
	//设置一个field的value
	SetValue: function (field, value, skipIfNotFound) {
		if (!field) return null;
		if (js.IsNull(value) && skipIfNotFound) return;
		var dateText = value;
		if (js.IsDate(value)) {
			//var testD = new Date();			
			if (value.prototype)
				dateText = value.format();
		}; //Get the text when value is Date
		if (field.setValue) {
			if (field.triggerClass == 'x-form-date-trigger')
				return field.setValue(dateText);
			return field.setValue(value);
		}
		if (field.SetValue) return field.SetValue(field);
		if (js.IsJQuery(field)) {
			field.each(function (index, item) {
				var tagName = item.tagName.toUpperCase();
				if (tagName == 'SPAN' || tagName == 'LABEL')
					$(item).text(value);
				else $(item).val(value);
			});
			return;
		}
		if (field.length && field.val) { return field.val(dateText); }
		if (field.length && field.attr) { return field.attr('value', dateText); }
		if (js.IsString(field) || js.IsObject(field)) { return $(field).val(dateText); }
		field.value = value;
		return value;
	},
	//对一堆的field获取值。根据这些field的name，保存到目标的对象中
	//@skipNull 如果为true，这忽略掉为空的字段
	GetValueForFields: function (fields, ovalue, skipNull) {
		ovalue = ovalue || {};
		js.Each(fields, function (item, index, array) {
			if (!item) return null;
			if (item.getValueAsContainer) {
				item.getValueAsContainer(ovalue, skipNull);
			}
			else if (item.xtype == 'fieldcontainer' && item.getValue)
				item.getValue(ovalue, skipNull);
			else {
				var name = js.Controls.GetName(item);
				if (name) {
					var value = js.Controls.GetValue(item);
					if (value || value === false || value === 0 || !skipNull)
						ovalue[name] = value;
				}
				if (item.nameForDisplay && item.getValue) {
					var value = item.getValue();
					if (value || value === false || value === 0 || !skipNull)
						ovalue[item.nameForDisplay] = value;
				}
			}
			return item;
		});
		if (ovalue.commit) ovalue.commit();
		return ovalue;
	},
	//对一堆的field进行赋值。根据这些field的name, 从value中取相应的值，再赋值给field
	//@skipIfNotFound 如果找不到是否忽略掉，默认是false
	//@baseValue，是一个基准值，如果给了baseValue且字段的值不同，那么设置相关的样式
	SetValueForFields: function (value, fields, skipIfNotFound, baseValue) {
		value = js.Init.InitGlobalParams(value, null, null, true);
		var tempValue, name, tempBaseValue, isSame;
		js.Each(fields, function (field) {
			if (!field) return null;
			field.record = value;
			field.baseValue = baseValue;
			name = js.Controls.GetName(field);
			if (js.IsNull(name)) tempValue = value; //如果name为空，这个组件可能是一个容器组件
			else tempValue = js.IsNull(value) ? null : (value.get ? value.get(name) : value[name]);
			if (field.setValueAsContainer) {
				field.setValueAsContainer(value);
			}
			else {
				js.Controls.SetValueForField(field, tempValue, skipIfNotFound);
			}
			if (baseValue) {
				isSame = true;
				if (js.IsNull(name)) tempBaseValue = baseValue; //如果name为空，这个组件可能是一个容器组件
				else tempBaseValue = baseValue.get ? baseValue.get(name) : baseValue[name];
				isSame = js.IsEqualTo(tempValue, tempBaseValue, true);
				if (!isSame) {
					if (field.addCls) field.addCls('ex-diff-field');
					else if (field.addClass) field.addClass('ex-diff-field');
				}
				else {
					if (field.removeCls) field.removeCls('ex-diff-field');
					else if (field.removeClass) field.removeClass('ex-diff-field');
				}
			}
			if (value && field.getDisplayName && field.setText) {
				tempValue = value.get ? value.get(field.getDisplayName()) : value[field.getDisplayName()];
				field.setText(tempValue);
			}
			return field;
		}, this, true, false);
	},
	CreateToggleButton: function (color, text) {
		var s = '<div class="eui-toggle-button"><span class="icon ' + color + '"></span><span class="content">' + text + '</span></div>';
		return s;
	},
	/**
	* 通用的初始化操作
	* @className 类名,用于UIText来查找相关的类别
	* @configs 配置
	* @FilterControl，如果本控件为Grid, 那么可以设置需要添加到本Grid的过滤组件
	* @ListControl 列表控件
	* @addCommonFunctions 添加公用的附加方法
	*/
	CommonInit: function (container, className, configs, FilterControl, ListControl, addCommonFunctions) {
		container.className = className;
		js.CopyObject(configs, container, true);
		js.Controls.AttachFilterControl(container, FilterControl);
		js.Controls.AttachListControl(container, ListControl);

		if (!container.getFields) container.getFields = js.EmptyFn;
		if (!container.setValue) container.setValue = function (value) {
			js.Controls.SetValueForFields(value, this.getFields());
		};
		if (!container.getValue) container.getValue = function (value) {
			return js.Controls.GetValueForFields(this.getFields(), value);
		};
		if (!container.setCreateMode) container.setCreateMode = js.EmptyFn;
		className = container.className;
		if (className && !window.eui[className]) {
			//var url = js.Web.GenerateAbsoluteUrl('locale/' + className + '.' + js.Sys.lang.replace('-','_') + '.js');
			//js.Web.LoadScript(url);
		}
		return container;
	},
	//将control附加到buttons中，修改button的OwnerControl属性，这是因为有些button事件用到了这个变量，所以可以这么弄
	AttachControl2Buttons: function (control, buttons) {
		if (control && buttons && buttons.length) {
			for (var i = 0; i < buttons.length; i++)
				buttons[i].OwnerControl = control;
		}
	},
	//对于container指定的对象，添加一个支持筛选的控件， 同时设置container为自动保存查询参数
	AttachFilterControl: function (container, FilterControl) {
		if (!FilterControl) return container;
		container.FilterControl = FilterControl;
		if (!FilterControl._RelatedControls) FilterControl._RelatedControls = [];
		FilterControl._RelatedControls.push(container);
		//设置grid为自动保存参数的，因为一般使用了AttachFilterControl的都是一个页面主要的查询组件
		if (container.initAsAutoStoreParams)
			container.initAsAutoStoreParams(FilterControl);
		return container;
	},
	AttachListControl: function (container, ListControl) {
		if (!ListControl) return container;
		container.ListControl = ListControl;
		js.Apply(container.Methods, ListControl.Methods);
		if (!FilterControl._RelatedControls) FilterControl._RelatedControls = [];
		FilterControl._RelatedControls.push(container);
		return container;
	},
	//自定义的提示
	TooltipControl: function () {
		var me = this, sme = js.Controls.ScheduleAsCureUnit; me.renderTo = null;
		me.ui = { el: null, headerEl: null, footerEl: null, bodyEl: null, buttons: [] };
		me.buttonConfigs = []; me.item = null; me.clipboardItem = null;
		me.initButton = function (index, text, cls, handler, scope, configs) {
			configs = configs || {};
			if (text) configs.text = text;
			if (cls) configs.cls = cls;
			if (handler) configs.handler = handler;
			if (scope) configs.scope = scope;
			configs.index = index;
			if (index >= me.buttonConfigs.length) me.buttonConfigs.length = index + 1;
			me.buttonConfigs[index] = configs;
			if (me.ui.buttons[index]) {
				var btn = me.ui.buttons[index];
				me.syncButtonConfig(btn, configs);
				btn.show();
			}
		}
		me.showButton = function (index, visible) {
			var btn = me.ui.buttons[index];
			if (btn) if (visible) btn.show(); else btn.hide()
		}
		me.syncButtonConfig = function (el, configs) {
			if (!el || !configs) return;
			el.html(configs.text);
			if (configs.cls) el.addClass(configs.cls);
			el.click(function () { });
		}
		me.setHeader = function (header) { if (me.ui.headerEl) me.ui.headerEl.html(header); }
		me.setFooter = function (footer) { if (me.ui.footerEl) me.ui.footerEl.html(footer); }
		me.setBody = function (body) {
			if (me.ui.bodyEl) {
				if (js.IsArray(body) && !js.IsString(body)) {
					var text = '', item;
					for (var i = 0; i < body.length; i++) {
						item = body[i];
						text += '<div class="item ' + (item.cls || '') + '"><span class="label">' + item.label +
							'</span><span class="field">' + item.field + '</span></div>';
					}
					body = text;
				}
				me.ui.bodyEl.html(body);
				if (body) me.ui.bodyEl.show(); else me.ui.bodyEl.hide();
			}
		}
		me.show = function (relatedElement) {
			relatedElement = relatedElement || this;
			if (!relatedElement.position) relatedElement = $(relatedElement);
			var pos = relatedElement.offset(), width = relatedElement.width(), height = relatedElement.height(),
				jWindow = $(window), jWidth = jWindow.width(), jHeight = jWindow.height(), left = pos.left, top = pos.top + height + 2;
			if (me.ui.el) me.ui.el.show();
			if (left || left === 0) me.ui.el.css('left', left);
			if (top || top === 0) me.ui.el.css('top', top);
		}
		me.hide = function () { if (me.ui.el) me.ui.el.hide(); }
		me.render = function () {
			me.ui.el = $('<div class="ex-tooltip msg qtip-target"><div class="header"></div><div class="body"></div><div class="footer"></div></div>').appendTo($('body'));
			me.ui.headerEl = me.ui.el.find('.header');
			me.ui.bodyEl = me.ui.el.find('.body');
			me.ui.footerEl = me.ui.el.find('.footer');
			me.setFooter('<span class="button button1">按钮1</span><span class="button button2">按钮2</span><span class="button button3">按钮3</span><span class="button button4">按钮4</span>');
			var buttons = me.ui.footerEl.find('.button');
			for (var i = 0; i < buttons.length; i++) {
				var btn = $(buttons[i]);
				btn.hide();
				me.ui.buttons.push(btn);
			}
			me.ui.el.hide();
		}
	}
}
var eui = {};
js.UI.RegisterResource(eui);
