package com.gtmdmock.admin.model.entity;

import java.util.ArrayList;
import java.util.List;

public class OverrideForwardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OverrideForwardExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRequestIdIsNull() {
            addCriterion("request_id is null");
            return (Criteria) this;
        }

        public Criteria andRequestIdIsNotNull() {
            addCriterion("request_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequestIdEqualTo(Integer value) {
            addCriterion("request_id =", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotEqualTo(Integer value) {
            addCriterion("request_id <>", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdGreaterThan(Integer value) {
            addCriterion("request_id >", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("request_id >=", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLessThan(Integer value) {
            addCriterion("request_id <", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLessThanOrEqualTo(Integer value) {
            addCriterion("request_id <=", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdIn(List<Integer> values) {
            addCriterion("request_id in", values, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotIn(List<Integer> values) {
            addCriterion("request_id not in", values, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdBetween(Integer value1, Integer value2) {
            addCriterion("request_id between", value1, value2, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotBetween(Integer value1, Integer value2) {
            addCriterion("request_id not between", value1, value2, "requestId");
            return (Criteria) this;
        }

        public Criteria andHostIsNull() {
            addCriterion("host is null");
            return (Criteria) this;
        }

        public Criteria andHostIsNotNull() {
            addCriterion("host is not null");
            return (Criteria) this;
        }

        public Criteria andHostEqualTo(String value) {
            addCriterion("host =", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotEqualTo(String value) {
            addCriterion("host <>", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostGreaterThan(String value) {
            addCriterion("host >", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostGreaterThanOrEqualTo(String value) {
            addCriterion("host >=", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLessThan(String value) {
            addCriterion("host <", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLessThanOrEqualTo(String value) {
            addCriterion("host <=", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLike(String value) {
            addCriterion("host like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotLike(String value) {
            addCriterion("host not like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostIn(List<String> values) {
            addCriterion("host in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotIn(List<String> values) {
            addCriterion("host not in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostBetween(String value1, String value2) {
            addCriterion("host between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotBetween(String value1, String value2) {
            addCriterion("host not between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andIsSecureIsNull() {
            addCriterion("is_secure is null");
            return (Criteria) this;
        }

        public Criteria andIsSecureIsNotNull() {
            addCriterion("is_secure is not null");
            return (Criteria) this;
        }

        public Criteria andIsSecureEqualTo(Integer value) {
            addCriterion("is_secure =", value, "isSecure");
            return (Criteria) this;
        }

        public Criteria andIsSecureNotEqualTo(Integer value) {
            addCriterion("is_secure <>", value, "isSecure");
            return (Criteria) this;
        }

        public Criteria andIsSecureGreaterThan(Integer value) {
            addCriterion("is_secure >", value, "isSecure");
            return (Criteria) this;
        }

        public Criteria andIsSecureGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_secure >=", value, "isSecure");
            return (Criteria) this;
        }

        public Criteria andIsSecureLessThan(Integer value) {
            addCriterion("is_secure <", value, "isSecure");
            return (Criteria) this;
        }

        public Criteria andIsSecureLessThanOrEqualTo(Integer value) {
            addCriterion("is_secure <=", value, "isSecure");
            return (Criteria) this;
        }

        public Criteria andIsSecureIn(List<Integer> values) {
            addCriterion("is_secure in", values, "isSecure");
            return (Criteria) this;
        }

        public Criteria andIsSecureNotIn(List<Integer> values) {
            addCriterion("is_secure not in", values, "isSecure");
            return (Criteria) this;
        }

        public Criteria andIsSecureBetween(Integer value1, Integer value2) {
            addCriterion("is_secure between", value1, value2, "isSecure");
            return (Criteria) this;
        }

        public Criteria andIsSecureNotBetween(Integer value1, Integer value2) {
            addCriterion("is_secure not between", value1, value2, "isSecure");
            return (Criteria) this;
        }

        public Criteria andDelayIsNull() {
            addCriterion("delay is null");
            return (Criteria) this;
        }

        public Criteria andDelayIsNotNull() {
            addCriterion("delay is not null");
            return (Criteria) this;
        }

        public Criteria andDelayEqualTo(Integer value) {
            addCriterion("delay =", value, "delay");
            return (Criteria) this;
        }

        public Criteria andDelayNotEqualTo(Integer value) {
            addCriterion("delay <>", value, "delay");
            return (Criteria) this;
        }

        public Criteria andDelayGreaterThan(Integer value) {
            addCriterion("delay >", value, "delay");
            return (Criteria) this;
        }

        public Criteria andDelayGreaterThanOrEqualTo(Integer value) {
            addCriterion("delay >=", value, "delay");
            return (Criteria) this;
        }

        public Criteria andDelayLessThan(Integer value) {
            addCriterion("delay <", value, "delay");
            return (Criteria) this;
        }

        public Criteria andDelayLessThanOrEqualTo(Integer value) {
            addCriterion("delay <=", value, "delay");
            return (Criteria) this;
        }

        public Criteria andDelayIn(List<Integer> values) {
            addCriterion("delay in", values, "delay");
            return (Criteria) this;
        }

        public Criteria andDelayNotIn(List<Integer> values) {
            addCriterion("delay not in", values, "delay");
            return (Criteria) this;
        }

        public Criteria andDelayBetween(Integer value1, Integer value2) {
            addCriterion("delay between", value1, value2, "delay");
            return (Criteria) this;
        }

        public Criteria andDelayNotBetween(Integer value1, Integer value2) {
            addCriterion("delay not between", value1, value2, "delay");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("path is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("path is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("path =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("path <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("path >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("path >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("path <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("path <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("path like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("path not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("path in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("path not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("path between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("path not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andMethodIsNull() {
            addCriterion("method is null");
            return (Criteria) this;
        }

        public Criteria andMethodIsNotNull() {
            addCriterion("method is not null");
            return (Criteria) this;
        }

        public Criteria andMethodEqualTo(String value) {
            addCriterion("method =", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotEqualTo(String value) {
            addCriterion("method <>", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThan(String value) {
            addCriterion("method >", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThanOrEqualTo(String value) {
            addCriterion("method >=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThan(String value) {
            addCriterion("method <", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThanOrEqualTo(String value) {
            addCriterion("method <=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLike(String value) {
            addCriterion("method like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotLike(String value) {
            addCriterion("method not like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodIn(List<String> values) {
            addCriterion("method in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotIn(List<String> values) {
            addCriterion("method not in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodBetween(String value1, String value2) {
            addCriterion("method between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotBetween(String value1, String value2) {
            addCriterion("method not between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andBodyIsNull() {
            addCriterion("body is null");
            return (Criteria) this;
        }

        public Criteria andBodyIsNotNull() {
            addCriterion("body is not null");
            return (Criteria) this;
        }

        public Criteria andBodyEqualTo(String value) {
            addCriterion("body =", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotEqualTo(String value) {
            addCriterion("body <>", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyGreaterThan(String value) {
            addCriterion("body >", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyGreaterThanOrEqualTo(String value) {
            addCriterion("body >=", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLessThan(String value) {
            addCriterion("body <", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLessThanOrEqualTo(String value) {
            addCriterion("body <=", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLike(String value) {
            addCriterion("body like", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotLike(String value) {
            addCriterion("body not like", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyIn(List<String> values) {
            addCriterion("body in", values, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotIn(List<String> values) {
            addCriterion("body not in", values, "body");
            return (Criteria) this;
        }

        public Criteria andBodyBetween(String value1, String value2) {
            addCriterion("body between", value1, value2, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotBetween(String value1, String value2) {
            addCriterion("body not between", value1, value2, "body");
            return (Criteria) this;
        }

        public Criteria andHeadersIsNull() {
            addCriterion("headers is null");
            return (Criteria) this;
        }

        public Criteria andHeadersIsNotNull() {
            addCriterion("headers is not null");
            return (Criteria) this;
        }

        public Criteria andHeadersEqualTo(String value) {
            addCriterion("headers =", value, "headers");
            return (Criteria) this;
        }

        public Criteria andHeadersNotEqualTo(String value) {
            addCriterion("headers <>", value, "headers");
            return (Criteria) this;
        }

        public Criteria andHeadersGreaterThan(String value) {
            addCriterion("headers >", value, "headers");
            return (Criteria) this;
        }

        public Criteria andHeadersGreaterThanOrEqualTo(String value) {
            addCriterion("headers >=", value, "headers");
            return (Criteria) this;
        }

        public Criteria andHeadersLessThan(String value) {
            addCriterion("headers <", value, "headers");
            return (Criteria) this;
        }

        public Criteria andHeadersLessThanOrEqualTo(String value) {
            addCriterion("headers <=", value, "headers");
            return (Criteria) this;
        }

        public Criteria andHeadersLike(String value) {
            addCriterion("headers like", value, "headers");
            return (Criteria) this;
        }

        public Criteria andHeadersNotLike(String value) {
            addCriterion("headers not like", value, "headers");
            return (Criteria) this;
        }

        public Criteria andHeadersIn(List<String> values) {
            addCriterion("headers in", values, "headers");
            return (Criteria) this;
        }

        public Criteria andHeadersNotIn(List<String> values) {
            addCriterion("headers not in", values, "headers");
            return (Criteria) this;
        }

        public Criteria andHeadersBetween(String value1, String value2) {
            addCriterion("headers between", value1, value2, "headers");
            return (Criteria) this;
        }

        public Criteria andHeadersNotBetween(String value1, String value2) {
            addCriterion("headers not between", value1, value2, "headers");
            return (Criteria) this;
        }

        public Criteria andCookiesIsNull() {
            addCriterion("cookies is null");
            return (Criteria) this;
        }

        public Criteria andCookiesIsNotNull() {
            addCriterion("cookies is not null");
            return (Criteria) this;
        }

        public Criteria andCookiesEqualTo(String value) {
            addCriterion("cookies =", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesNotEqualTo(String value) {
            addCriterion("cookies <>", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesGreaterThan(String value) {
            addCriterion("cookies >", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesGreaterThanOrEqualTo(String value) {
            addCriterion("cookies >=", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesLessThan(String value) {
            addCriterion("cookies <", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesLessThanOrEqualTo(String value) {
            addCriterion("cookies <=", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesLike(String value) {
            addCriterion("cookies like", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesNotLike(String value) {
            addCriterion("cookies not like", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesIn(List<String> values) {
            addCriterion("cookies in", values, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesNotIn(List<String> values) {
            addCriterion("cookies not in", values, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesBetween(String value1, String value2) {
            addCriterion("cookies between", value1, value2, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesNotBetween(String value1, String value2) {
            addCriterion("cookies not between", value1, value2, "cookies");
            return (Criteria) this;
        }

        public Criteria andQueryParamsIsNull() {
            addCriterion("query_params is null");
            return (Criteria) this;
        }

        public Criteria andQueryParamsIsNotNull() {
            addCriterion("query_params is not null");
            return (Criteria) this;
        }

        public Criteria andQueryParamsEqualTo(String value) {
            addCriterion("query_params =", value, "queryParams");
            return (Criteria) this;
        }

        public Criteria andQueryParamsNotEqualTo(String value) {
            addCriterion("query_params <>", value, "queryParams");
            return (Criteria) this;
        }

        public Criteria andQueryParamsGreaterThan(String value) {
            addCriterion("query_params >", value, "queryParams");
            return (Criteria) this;
        }

        public Criteria andQueryParamsGreaterThanOrEqualTo(String value) {
            addCriterion("query_params >=", value, "queryParams");
            return (Criteria) this;
        }

        public Criteria andQueryParamsLessThan(String value) {
            addCriterion("query_params <", value, "queryParams");
            return (Criteria) this;
        }

        public Criteria andQueryParamsLessThanOrEqualTo(String value) {
            addCriterion("query_params <=", value, "queryParams");
            return (Criteria) this;
        }

        public Criteria andQueryParamsLike(String value) {
            addCriterion("query_params like", value, "queryParams");
            return (Criteria) this;
        }

        public Criteria andQueryParamsNotLike(String value) {
            addCriterion("query_params not like", value, "queryParams");
            return (Criteria) this;
        }

        public Criteria andQueryParamsIn(List<String> values) {
            addCriterion("query_params in", values, "queryParams");
            return (Criteria) this;
        }

        public Criteria andQueryParamsNotIn(List<String> values) {
            addCriterion("query_params not in", values, "queryParams");
            return (Criteria) this;
        }

        public Criteria andQueryParamsBetween(String value1, String value2) {
            addCriterion("query_params between", value1, value2, "queryParams");
            return (Criteria) this;
        }

        public Criteria andQueryParamsNotBetween(String value1, String value2) {
            addCriterion("query_params not between", value1, value2, "queryParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsIsNull() {
            addCriterion("path_params is null");
            return (Criteria) this;
        }

        public Criteria andPathParamsIsNotNull() {
            addCriterion("path_params is not null");
            return (Criteria) this;
        }

        public Criteria andPathParamsEqualTo(String value) {
            addCriterion("path_params =", value, "pathParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsNotEqualTo(String value) {
            addCriterion("path_params <>", value, "pathParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsGreaterThan(String value) {
            addCriterion("path_params >", value, "pathParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsGreaterThanOrEqualTo(String value) {
            addCriterion("path_params >=", value, "pathParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsLessThan(String value) {
            addCriterion("path_params <", value, "pathParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsLessThanOrEqualTo(String value) {
            addCriterion("path_params <=", value, "pathParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsLike(String value) {
            addCriterion("path_params like", value, "pathParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsNotLike(String value) {
            addCriterion("path_params not like", value, "pathParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsIn(List<String> values) {
            addCriterion("path_params in", values, "pathParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsNotIn(List<String> values) {
            addCriterion("path_params not in", values, "pathParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsBetween(String value1, String value2) {
            addCriterion("path_params between", value1, value2, "pathParams");
            return (Criteria) this;
        }

        public Criteria andPathParamsNotBetween(String value1, String value2) {
            addCriterion("path_params not between", value1, value2, "pathParams");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveIsNull() {
            addCriterion("is_keep_alive is null");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveIsNotNull() {
            addCriterion("is_keep_alive is not null");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveEqualTo(Integer value) {
            addCriterion("is_keep_alive =", value, "isKeepAlive");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveNotEqualTo(Integer value) {
            addCriterion("is_keep_alive <>", value, "isKeepAlive");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveGreaterThan(Integer value) {
            addCriterion("is_keep_alive >", value, "isKeepAlive");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_keep_alive >=", value, "isKeepAlive");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveLessThan(Integer value) {
            addCriterion("is_keep_alive <", value, "isKeepAlive");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveLessThanOrEqualTo(Integer value) {
            addCriterion("is_keep_alive <=", value, "isKeepAlive");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveIn(List<Integer> values) {
            addCriterion("is_keep_alive in", values, "isKeepAlive");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveNotIn(List<Integer> values) {
            addCriterion("is_keep_alive not in", values, "isKeepAlive");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveBetween(Integer value1, Integer value2) {
            addCriterion("is_keep_alive between", value1, value2, "isKeepAlive");
            return (Criteria) this;
        }

        public Criteria andIsKeepAliveNotBetween(Integer value1, Integer value2) {
            addCriterion("is_keep_alive not between", value1, value2, "isKeepAlive");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}