$(function () {
    var examId = $("#examId").val();
    //请求题目基本信息
    $.post("teacher/exam/get/"+examId, "", function (data) {
        if (data.code != 200) {
            Tips.showError("获取失败，请稍候再试");
        } else {
            $("#exam_details").html("<span>试卷："+data.data.examTitle+"</span>"+
                "<span> | 总分："+data.data.point+"</span>"+
                "<span> | 考试时间："+data.data.examTime+"</span>"+
                "<span> | 单选题："+data.data.singleSize+"</span>"+
                "<span> | 多选题："+data.data.multiSize+"</span>"+
                "<span> | 判断题："+data.data.jugdeSize+"</span>"
            );
        }
    }, "json");

    //显示添加单选题模态框
    $("#addSingleModal").click(function () {
        $('#single_answer').show();
        $('#multi_answer').hide();
        $('#type').val("SINGLE");
        $("#singleModal").modal("show");
    });

    //显示添加多选题模态框
    $("#addMultiModal").click(function () {
        $('#multi_answer').show();
        $('#type').val("MULTI");
        $('#single_answer').hide();
        $("#singleModal").modal("show");
    });


    //添加判断题
    $("#addJudgeQuestion").click(function () {
        var data = "title=" + $("#judge_question").val() +
            "&answer=" + $("#judge_answer").val() +
            "&point=" + $("#judge_point").val() +
            "&type=JUDGE" +
            "&examId=" + $("#examId").val();
        //提交
        $.post("teacher/question/save", data, function (data) {
            if (data.code != 200) {
                Tips.showError("保存失败，请稍候再试");
            } else {
                $("#judgeModal").modal("hide");
                Tips.showSuccess("保存成功");
                setTimeout(function () {
                    window.location.reload();
                }, 3000);
            }
        }, "json");
    });


    //添加选择题，包含单选和多选
    $("#addMultiQuestion").click(function () {
        var type = $("#type").val();
        var answer;
        //获取单选题的选中项
        if (type === 'SINGLE') {
            var $checkeds = $("#single_answer input:checked");
            answer = $checkeds[0].value;
        } else {
            var $checkeds = $("#multi_answer input:checked");
            if ($checkeds.length < 2) {
                $("#choice-error").html("请选择至少两个答案");
                return;
            }
            //获取多选题的选中项，拼接答案串
            var array = [];
            for (i = 0, l = $checkeds.length; i < l; i++) {
                array.push($checkeds[i].value);
            }
            answer = array.join(",");
        }

        //参数串
        var data = "title=" + $("#choice_question").val() +
            "&optionA=" + $("#optionA").val() +
            "&optionB=" + $("#optionB").val() +
            "&optionC=" + $("#optionC").val() +
            "&optionD=" + $("#optionD").val() +
            "&point=" + $("#point").val() +
            "&answer=" + answer +
            "&examId=" + $("#examId").val() +
            "&type=" + type;

        //提交
        $.post("teacher/question/save", data, function (data) {
            if (data.code != 200) {
                Tips.showError("保存失败，请稍候再试");
            } else {
                $("#singleModal").modal("hide");
                Tips.showSuccess("保存成功");
                setTimeout(function () {
                    window.location.reload();
                }, 3000);
            }
        }, "json");
    });

    //显示添加单选题模态框
    $("#btn_select_question").click(function () {
        var json = [];
        $("#data_list tr").each(function (i) {
            if (i != 0) {
                var qid = $(this).children().attr("qid");
                var type = $(this).children().attr("type");
                var j = {};
                j.id = qid;
                j.type = type;
                json.push(j);
            }
        });
        var type = $("#question_type").val();
        var obj = {};
        obj.types = json;
        obj.type = type;

        if (type == -1) {
            $("#bank-container").html("请选择类型");
            return;
        }

        $.ajax({
            type: "POST",
            url: "teacher/question/repository",
            data: JSON.stringify(obj),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data.code != 200) {
                    Tips.showError("失败，请稍候再试");
                } else {
                    if (data.data.length == 0) {
                        $("#bank-container").html("无题目数据");
                    }else{
                        var str = "", item;
                        for (var i = 0, l = data.data.length; i < l; i++) {
                            item = data.data[i];
                            str += "<input type='checkbox' name='q' qid='" + item.id + "' point='" + item.point + "'><span>" + item.title + "</span>";
                        }
                        $(str).appendTo($("#bank-container").empty());
                    }
                }
            }
        });
    });
    //显示添加单选题模态框
    $("#addQuestion").click(function () {
        var json = [];
        $("input:checkbox:checked").each(function () {
            var qid = $(this).attr("qid");
            var point = $(this).attr("point");
            // var qtype = $(this).attr("qtype");
            var j = {};
            j.id = qid;
            j.point = point;
            // j.type = qtype;
            json.push(j);
        });
        var obj = {};
        obj.types = json;
        obj.examId = $("#examId").val();
        obj.type = $("#question_type option:selected").val();
        $.ajax({
            type: "POST",
            url: "teacher/question/addExamQuestion",
            data: JSON.stringify(obj),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data.code != 200) {
                    Tips.showError("失败，请稍候再试");
                } else {
                    $("#question_bank").modal("hide");
                    Tips.showSuccess("保存成功");
                    setTimeout(function () {
                        window.location.reload();
                    }, 3000);
                }
            }
        });
    });
});

