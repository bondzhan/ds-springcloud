package org.bond.yy.elasticsearch.config.autobuild;

import org.bond.yy.elasticsearch.config.EsCriteria;

public enum BuildPolicy {
	
	MUST{
		public void build(EsCriteria criteria, EsMark mark, Object fieldValue) {
			Object esFieldName = mark.field();
			criteria.must(esFieldName, fieldValue);
		}
	},
	
	MUST_TEXT{
		public void build(EsCriteria criteria, EsMark mark, Object fieldValue) {
			Object esFieldName = mark.field();
			criteria.mustText(esFieldName, fieldValue);
		}
	},
	
	KEY_WORD{
		public void build(EsCriteria criteria, EsMark mark, Object fieldValue) {
			Object esFieldName = mark.field();
			criteria.keyWord(fieldValue, esFieldName);
		}
	},
	
	SOUCE_INCLUDE{
		public void build(EsCriteria criteria, EsMark mark, Object fieldValue) {
			criteria._sourceInclude(fieldValue);
		}
	};
	
	abstract public void build(EsCriteria criteria, EsMark mark, Object fieldValue);
	
	


}
