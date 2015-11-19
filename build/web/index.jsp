<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Zillow Estimate</h3>
        </div>
        <div class="panel-body">

            <form role="form" action="ZestimateController" method="GEt">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12">

                        <div class="form-group">
                            <input type="address" name="address" id="address" class="form-control input-sm" placeholder="address" required>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <div class="form-group">
                            <input type="city" name="city" id="city" class="form-control input-sm" placeholder="city" required>
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <div class="form-group">
                            <input type="state" name="state" id="state" class="form-control input-sm" placeholder="state" required>
                        </div>
                    </div>

                </div>
             
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <div class="form-group">
                            <input type="zipcode" name="zipcode" id="zipcode" class="form-control input-sm" placeholder="zipcode" required>
                        </div>
                    </div>

                </div>
                <input type="submit" value="Register" class="btn btn-info btn-block">

            </form>

            </form>
        </div>
    </div>
</div>


</div> <!-- /container -->

</div> <!-- /container -->

<%@include file="footer.jsp" %>