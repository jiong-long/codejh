<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计算属性和侦听器</title>
<script src="/jiong/js/Vue/vue.js"></script>
</head>
<body>
	<!-- 计算属性 -->
	<div id="example">
	  	<p>Original message: "{{ message }}"</p>
	  	<p>Computed reversed message: "{{ reversedMessage }}"</p>
	</div>
	
	<div id="watch-example">
  		<p>
    		Ask a yes/no question:
    		<input v-model="question">
  		</p>
  		<p>{{ answer }}</p>
	</div>
	
	<!-- if语句 -->
	<div id="app">
	    <div v-if="Math.random() > 0.5">
	      Sorry
	    </div>
	    <div v-else>
	      Not sorry
	    </div>
	</div>
	
	<!-- 循环语句 -->
	<div id="app1">
	  <ol>
	    <li v-for="site in sites">
	      {{ site.name }}
	    </li>
	  </ol>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
	<script type="text/javascript">
		new Vue({
			el: '#app'
		})
		
		new Vue({
		  el: '#app1',
		  data: {
		    sites: [
		      { name: 'Runoob' },
		      { name: 'Google' },
		      { name: 'Taobao' }
		    ]
		  }
		})
		
		
		var watchExampleVM = new Vue({
		  el: '#watch-example',
		  data: {
		    question: '',
		    answer: 'I cannot give you an answer until you ask a question!'
		  },
		  watch: {
		    // 如果 `question` 发生改变，这个函数就会运行
		    question: function (newQuestion, oldQuestion) {
		      this.answer = 'Waiting for you to stop typing...'
		      this.debouncedGetAnswer()
		    }
		  },
		  created: function () {
		    // '_.debounce' 是一个通过 Lodash 限制操作频率的函数。
		    // 在这个例子中，我们希望限制访问 yesno.wtf/api 的频率
		    // AJAX 请求直到用户输入完毕才会发出。想要了解更多关于
		    // '_.debounce' 函数 (及其近亲 '_.throttle') 的知识，
		    // 请参考：https://lodash.com/docs#debounce
		    this.debouncedGetAnswer = _.debounce(this.getAnswer, 500)
		  },
		  methods: {
		    getAnswer: function () {
		      if (this.question.indexOf('?') === -1) {
		        this.answer = 'Questions usually contain a question mark. ;-)'
		        return
		      }
		      this.answer = 'Thinking...'
		      var vm = this
		      axios.get('https://yesno.wtf/api')
		        .then(function (response) {
		          vm.answer = _.capitalize(response.data.answer)
		        })
		        .catch(function (error) {
		          vm.answer = 'Error! Could not reach the API. ' + error
		        })
		    }
		  }
		})
	
		var vm = new Vue({
		  	el: '#example',
		  	data: {
		    	message: 'Hello'
		  	},
		  	computed: {
		    	// 计算属性的 getter
		    	reversedMessage: function () {
		      		// this 指向 vm 实例
		      		return this.message.split('').reverse().join('')
		    	}
		  	}
		})
	</script>
</body>
</html>