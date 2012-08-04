<div class="input-prepend">
	<span class="add-on"><i class="icon-envelope"></i></span>
<%
	def attrs = [type: 'email', name: property, value: value]
	if (required) attrs.required = ''
	out << g.field(attrs)
%>
</div>

          <ul id="tab" class="nav nav-tabs">
            <li class="active"><a href="#editor-tab" data-toggle="tab">Editor</a></li>
            <li class=""><a href="#preview-tab" data-toggle="tab">preview</a></li>
          </ul>
          <div id="myTabContent" class="tab-content">
            
            <div class="tab-pane fade active in" id="editor-tab">
              <div id="editor">
			<textarea id="markdown-editor" name="${property.type }">
${value }
			</textarea>
		</div>
            </div>
            
            <div class="tab-pane fade" id="preview-tab">
              <div id="result">
		</div>
            </div>
          </div>
    	<hr/>