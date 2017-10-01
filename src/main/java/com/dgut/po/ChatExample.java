package com.dgut.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChatExample() {
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

        public Criteria andMessagesIsNull() {
            addCriterion("messages is null");
            return (Criteria) this;
        }

        public Criteria andMessagesIsNotNull() {
            addCriterion("messages is not null");
            return (Criteria) this;
        }

        public Criteria andMessagesEqualTo(Integer value) {
            addCriterion("messages =", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesNotEqualTo(Integer value) {
            addCriterion("messages <>", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesGreaterThan(Integer value) {
            addCriterion("messages >", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesGreaterThanOrEqualTo(Integer value) {
            addCriterion("messages >=", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesLessThan(Integer value) {
            addCriterion("messages <", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesLessThanOrEqualTo(Integer value) {
            addCriterion("messages <=", value, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesIn(List<Integer> values) {
            addCriterion("messages in", values, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesNotIn(List<Integer> values) {
            addCriterion("messages not in", values, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesBetween(Integer value1, Integer value2) {
            addCriterion("messages between", value1, value2, "messages");
            return (Criteria) this;
        }

        public Criteria andMessagesNotBetween(Integer value1, Integer value2) {
            addCriterion("messages not between", value1, value2, "messages");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentIsNull() {
            addCriterion("last_active_content is null");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentIsNotNull() {
            addCriterion("last_active_content is not null");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentEqualTo(String value) {
            addCriterion("last_active_content =", value, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentNotEqualTo(String value) {
            addCriterion("last_active_content <>", value, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentGreaterThan(String value) {
            addCriterion("last_active_content >", value, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentGreaterThanOrEqualTo(String value) {
            addCriterion("last_active_content >=", value, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentLessThan(String value) {
            addCriterion("last_active_content <", value, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentLessThanOrEqualTo(String value) {
            addCriterion("last_active_content <=", value, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentLike(String value) {
            addCriterion("last_active_content like", value, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentNotLike(String value) {
            addCriterion("last_active_content not like", value, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentIn(List<String> values) {
            addCriterion("last_active_content in", values, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentNotIn(List<String> values) {
            addCriterion("last_active_content not in", values, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentBetween(String value1, String value2) {
            addCriterion("last_active_content between", value1, value2, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveContentNotBetween(String value1, String value2) {
            addCriterion("last_active_content not between", value1, value2, "lastActiveContent");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeIsNull() {
            addCriterion("last_active_time is null");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeIsNotNull() {
            addCriterion("last_active_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeEqualTo(Date value) {
            addCriterion("last_active_time =", value, "lastActiveTime");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeNotEqualTo(Date value) {
            addCriterion("last_active_time <>", value, "lastActiveTime");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeGreaterThan(Date value) {
            addCriterion("last_active_time >", value, "lastActiveTime");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_active_time >=", value, "lastActiveTime");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeLessThan(Date value) {
            addCriterion("last_active_time <", value, "lastActiveTime");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_active_time <=", value, "lastActiveTime");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeIn(List<Date> values) {
            addCriterion("last_active_time in", values, "lastActiveTime");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeNotIn(List<Date> values) {
            addCriterion("last_active_time not in", values, "lastActiveTime");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeBetween(Date value1, Date value2) {
            addCriterion("last_active_time between", value1, value2, "lastActiveTime");
            return (Criteria) this;
        }

        public Criteria andLastActiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_active_time not between", value1, value2, "lastActiveTime");
            return (Criteria) this;
        }

        public Criteria andUnreadIsNull() {
            addCriterion("unread is null");
            return (Criteria) this;
        }

        public Criteria andUnreadIsNotNull() {
            addCriterion("unread is not null");
            return (Criteria) this;
        }

        public Criteria andUnreadEqualTo(Integer value) {
            addCriterion("unread =", value, "unread");
            return (Criteria) this;
        }

        public Criteria andUnreadNotEqualTo(Integer value) {
            addCriterion("unread <>", value, "unread");
            return (Criteria) this;
        }

        public Criteria andUnreadGreaterThan(Integer value) {
            addCriterion("unread >", value, "unread");
            return (Criteria) this;
        }

        public Criteria andUnreadGreaterThanOrEqualTo(Integer value) {
            addCriterion("unread >=", value, "unread");
            return (Criteria) this;
        }

        public Criteria andUnreadLessThan(Integer value) {
            addCriterion("unread <", value, "unread");
            return (Criteria) this;
        }

        public Criteria andUnreadLessThanOrEqualTo(Integer value) {
            addCriterion("unread <=", value, "unread");
            return (Criteria) this;
        }

        public Criteria andUnreadIn(List<Integer> values) {
            addCriterion("unread in", values, "unread");
            return (Criteria) this;
        }

        public Criteria andUnreadNotIn(List<Integer> values) {
            addCriterion("unread not in", values, "unread");
            return (Criteria) this;
        }

        public Criteria andUnreadBetween(Integer value1, Integer value2) {
            addCriterion("unread between", value1, value2, "unread");
            return (Criteria) this;
        }

        public Criteria andUnreadNotBetween(Integer value1, Integer value2) {
            addCriterion("unread not between", value1, value2, "unread");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdIsNull() {
            addCriterion("receiver_id is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIdIsNotNull() {
            addCriterion("receiver_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverIdEqualTo(Integer value) {
            addCriterion("receiver_id =", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotEqualTo(Integer value) {
            addCriterion("receiver_id <>", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdGreaterThan(Integer value) {
            addCriterion("receiver_id >", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("receiver_id >=", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdLessThan(Integer value) {
            addCriterion("receiver_id <", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdLessThanOrEqualTo(Integer value) {
            addCriterion("receiver_id <=", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdIn(List<Integer> values) {
            addCriterion("receiver_id in", values, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotIn(List<Integer> values) {
            addCriterion("receiver_id not in", values, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdBetween(Integer value1, Integer value2) {
            addCriterion("receiver_id between", value1, value2, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotBetween(Integer value1, Integer value2) {
            addCriterion("receiver_id not between", value1, value2, "receiverId");
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