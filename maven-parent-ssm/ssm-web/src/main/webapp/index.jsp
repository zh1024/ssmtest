<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link href="dep/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="dep/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 员工添加 -->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工添加</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="empName_add_input" class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<input type="text" name="empName" class="form-control"
									id="empName_add_input" placeholder="员工姓名"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="email_add_input" class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="email" name="email" class="form-control"
									id="email_add_input" placeholder="empName@qq.com"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="email_add_input" class="col-sm-2 control-label">gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_add" value="M" checked="checked">
									男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2_add" value="F"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="email_add_input" class="col-sm-2 control-label">gender</label>
							<div class="col-sm-4">
								<!-- 部门信息 -->
								<select class="form-control" name="dId" id="dept_add_select">

								</select>
							</div>
						</div>


					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_save">保存</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button type="button" class="btn btn-primary" id="emp_add">新增</button>
				<button type="button" class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 显示表格数据-->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
		<!-- 显示分页信息-->
		<div class="row">
			<div class="col-md-6" id="page_info"></div>
			<div class="col-md-6" id="page_nav"></div>
		</div>
	</div>
	<script type="text/javascript">
		//全局保存总记录数,用于添加数据后跳转

		var pageTolas;

		$(function() {
			to_page(1);
		});

		function to_page(pn) {
			$.ajax({
				url : "${APP_PATH }/emps",
				data : "pn=" + pn,
				type : "GET",
				success : function(result) {
					build_emps_table(result);
					build_page_info(result);
					build_page_nav(result);
				}
			});

		}
		function build_emps_table(result) {
			$("#emps_table tbody").empty();

			var emps = result.extend.PageInfo.list;
			$.each(emps, function(index, item) {
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var genderTd = $("<td></td>").append(
						item.gender == "M" ? "男" : "女");
				var emailTd = $("<td></td>").append(item.email);
				var deptNameTd = $("<td></td>")
						.append(item.department.deptName);

				var editBtn = $("<button</button>").addClass(
						"btn btn-primary btn-sm").append(
						$("<span></span>").addClass(
								"glyphicon glyphicon-pencil").append("编辑"));
				var delBtn = $("<button</button>").addClass(
						"btn btn-danger btn-sm").append(
						$("<span></span>")
								.addClass("glyphicon glyphicon-trash").append(
										"删除"));

				var btnTd = $("<td></td>").append(editBtn).append(" ").append(
						delBtn);

				$("<tr></tr>").append(empIdTd).append(empNameTd).append(
						genderTd).append(emailTd).append(deptNameTd).append(
						btnTd).appendTo("#emps_table tbody");
			});
		}
		function build_page_info(result) {

			$("#page_info").empty();
			//分页信息
			var pageInfo = result.extend.PageInfo;
			$("#page_info").append(
					"当前 " + pageInfo.pageNum + " 页,总共 " + pageInfo.pages
							+ "页,总共 " + pageInfo.total + "条记录");

			pageTolas = pageInfo.total;

		}
		function build_page_nav(result) {
			$("#page_nav").empty();

			//分页条
			var PageInfo = result.extend.PageInfo;
			var ul = $("<ul></ul>").addClass("pagination");
			//首页
			var firstPageLi = $("<li></li>").append(
					$("<a></a>").append("首页").attr("href", "#"));

			var preLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
			if (!PageInfo.hasPreviousPage) {
				firstPageLi.addClass("disabled");
				preLi.addClass("disabled");
			} else {
				firstPageLi.click(function() {
					to_page(1);
				});
				preLi.click(function() {
					to_page(PageInfo.pageNum - 1);
				});
			}

			//下一页
			var nextLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
			//尾页
			var lastPageLi = $("<li></li>").append(
					$("<a></a>").append("尾页").attr("href", "#"));

			if (!PageInfo.hasNextPage) {
				nextLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			} else {
				nextLi.click(function() {
					to_page(PageInfo.pageNum + 1);
				});
				lastPageLi.click(function() {
					to_page(PageInfo.pages);
				});
			}

			ul.append(firstPageLi).append(preLi);
			$.each(PageInfo.navigatepageNums, function(index, item) {

				var num = $("<li></li>").append($("<a></a>").append(item));
				if (PageInfo.pageNum == item) {
					num.addClass("active");
				}
				num.click(function() {
					to_page(item);
				});

				ul.append(num);
			});
			ul.append(nextLi).append(lastPageLi);
			var nav = $("<nav></nav>").append(ul).appendTo("#page_nav");
		}

		$("#emp_add").click(
				function() {
					//表单重置
					$('#empAddModal form')[0].reset();
					//重置表单样式
					$("#empAddModal form").find("*").removeClass(
							"has-success has-error");
					//表单信息提示框
					$("#empAddModal form").find(".help-block").text("");
					//点击新增按钮弹出模态框
					//查询出部门信息
					getDepts();

					$('#empAddModal').modal({
						backdrop : "static"
					});
				});

		function getDepts() {
			$("#dept_add_select").empty();

			//获取部门信息
			$.ajax({
				url : "${APP_PATH }/depts",
				type : "GET",
				success : function(result) {
					var depts = result.extend.depts;
					$.each(depts, function(index, item) {
						var option = $("<option></option>").append(
								item.deptName).attr("value", item.deptId);
						option.appendTo("#dept_add_select");
					});

				}
			});
		}

		function validate_add_form() {
			//校验表单数据
			//empName_add_input,email_add_input

			var empName = $("#empName_add_input").val();
			var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;

			if (!regName.test(empName)) {
				validate_msg("#empName_add_input", "error",
						"英文长度为6-16,中文长度为2-5");
				return false;
			} else {
				validate_msg("#empName_add_input", "success", "");
			}

			var email = $("#email_add_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!regEmail.test(email)) {
				validate_msg("#email_add_input", "error", "邮箱格式不正确");
				return false;
			} else {
				validate_msg("#email_add_input", "success", "");
			}
			return true;
		}
		function validate_msg(ele, status, msg) {
			//提示信息
			$(ele).parent().removeClass("has-error has-success");
			$(ele).next("span").text("");
			if (status == "success") {
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);

			} else if (status == "error") {
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
		}

		$("#empName_add_input").change(
				function() {
					var empName = this.value;
					//校验员工的唯一性
					$.ajax({
						url : "${APP_PATH }/checkUser",
						type : "POST",
						data : "empName=" + empName,
						success : function(result) {
							if (result.status) {
								validate_msg("#empName_add_input", "success",
										"用户名可用")
								$("#emp_save").attr("ajax-va", "success");
							} else {
								validate_msg("#empName_add_input", "error",
										result.extend.va_msg)
								$("#emp_save").attr("ajax-va", "error");
							}

						}
					});
				});

		$("#emp_save")
				.click(
						function() {

							//校验成功后保存
							if (!validate_add_form()) {
								return false;
							}

							if ($(this).attr("ajax-va") == "error") {

								return false;
							}
							//保存
							$
									.ajax({
										url : "${APP_PATH }/emp",
										type : "POST",
										data : $("#empAddModal  form")
												.serialize(),
										success : function(result) {
											if (result.status) {
												//员工保存成功
												//关闭模态框
												$('#empAddModal').modal("hide");
												//来到最后一页，显示刚才保存的数据
												to_page(pageTolas);
											} else {
												if (undefined != result.extend.fieldErrors.email) {
													validate_msg(
															"#email_add_input",
															"error",
															result.extend.fieldErrors.email);
												}
												if (undefined != result.extend.fieldErrors.empName) {
													validate_msg(
															"#empName_add_input",
															"error",
															result.extend.fieldErrors.empName);

												}
											}

										}
									});

						});
	</script>
</body>
</html>