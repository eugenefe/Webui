SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE BOND_HIS;
DROP TABLE BOND_INDEX_HIS;
DROP TABLE ETF_HIS;
DROP TABLE ETF_PDF;
DROP TABLE FUTURES_HIS;
DROP TABLE FX_RATE_HIS;
DROP TABLE INTERATE_RATE_HIS;
DROP TABLE IR_CURVE_HIS;
DROP TABLE MACRO_INDEX_HIS;
DROP TABLE OPTION_HIS;
DROP TABLE PORTFOLIO_CASHFLOW;
DROP TABLE PORTFOLIO_DETAIL;
DROP TABLE PORTFOLIO_RETURN;
DROP TABLE PORTFOLIO_RISK;
DROP TABLE PORTFOLIO_RISK_DETAIL;
DROP TABLE PORTFOLIO_SENSITIVITY;
DROP TABLE POSITION_CASHFLOW;
DROP TABLE POSITION_GREEKS;
DROP TABLE POSITION_IR_SENSITITIVY;
DROP TABLE POSITION_RETURN;
DROP TABLE POSITION_RISK_DETAIL;
DROP TABLE POSITION_RISK;
DROP TABLE PRODUCT_CASHFLOW;
DROP TABLE SCENARIO_HIS;
DROP TABLE STOCK_HIS;
DROP TABLE STOCK_INDEX_HIS;
DROP TABLE SYNTHETICS_HIS;
DROP TABLE VCV_MATRIX_HIS;
DROP TABLE BASEDATE;
DROP TABLE POSITION;
DROP TABLE EMPLOYEE;
DROP TABLE BIZUNIT;
DROP TABLE BOND_INDEX_DETAIL;
DROP TABLE BOND;
DROP TABLE BOND_INDEX;
DROP TABLE COUNTERPARTY;
DROP TABLE ETF;
DROP TABLE FN_ACCOUNT;
DROP TABLE FUTURES;
DROP TABLE FX_CASH;
DROP TABLE IRC_BUCKET_DETAIL;
DROP TABLE INTEREST_RATE;
DROP TABLE IRC_FUNCTION_DETAIL;
DROP TABLE IR_CURVE;
DROP TABLE MACRO_INDEX;
DROP TABLE OPTIONS;
DROP TABLE RISK_FACTOR;
DROP TABLE SCENARIO_DETAIL;
DROP TABLE STOCK_INDEX_DETAIL;
DROP TABLE STOCK;
DROP TABLE STOCK_INDEX;
DROP TABLE SYNTHETIC_DETAIL;
DROP TABLE SYNTHETICS;
DROP TABLE MARKET_VARIABLE;
DROP TABLE MATURITY;
DROP TABLE PORTFOLIO;
DROP TABLE SCENARIO;
DROP TABLE SCENARIO_SET;
DROP TABLE SCENARIO_VAR;
DROP TABLE STOCK_EXCHANGE;
DROP TABLE VAR_GEN_MCD;
DROP TABLE VCV_MATRIX;
DROP TABLE VOL_CURVE;




/* Create Tables */

CREATE TABLE BASEDATE
(
	BSSD VARCHAR(8) NOT NULL,
	IS_BIZDAY CHAR(1),
	PREV_BIZDAY VARCHAR(8),
	NEXT_BIZDAY VARCHAR(8),
	EOM_BIZDAY VARCHAR(8),
	EOQ_BIZDAY VARCHAR(8),
	-- END OF SEMI ANNUAL
	EOS_BIZDAY VARCHAR(8) COMMENT 'END OF SEMI ANNUAL',
	EOY_BIZDAY VARCHAR(8),
	FISCAL_BIZDAY VARCHAR(8),
	PRIMARY KEY (BSSD)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BIZUNIT
(
	ORG_ID VARCHAR(20) NOT NULL,
	ORG_NAME VARCHAR(50),
	PARENT_ORG_ID VARCHAR(20),
	PRIMARY KEY (ORG_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BOND
(
	BOND_ID VARCHAR(20) NOT NULL,
	ISSUER_ID VARCHAR(30),
	FACE_AMT DECIMAL(10,0),
	TOTAL_AMT DECIMAL(19,0),
	ISSUE_DATE VARCHAR(8),
	MATURITY_DATE VARCHAR(8),
	COUPON_RATE DECIMAL(10,8),
	INT_TERM VARCHAR(10),
	COMPOUND_MTD VARCHAR(10),
	DAYCOUNT_MTD VARCHAR(10),
	-- DISCOUNT, BULLET, FIXED, FLOATING, FIXED AMORT, FLOAT_AMORT, ...
	BOND_CF_TYPE VARCHAR(10) COMMENT 'DISCOUNT, BULLET, FIXED, FLOATING, FIXED AMORT, FLOAT_AMORT, ...',
	-- FISRT, SECONDARY
	PRIORITY_CD VARCHAR(10) COMMENT 'FISRT, SECONDARY',
	-- ABS, MBS, CDO,....
	SECURITIZATION_CD VARCHAR(10) COMMENT 'ABS, MBS, CDO,....',
	CREDIT_RATING_CD VARCHAR(10),
	CURR_CD VARCHAR(3),
	PREMIUM_AT_END DECIMAL(10,4),
	-- GOV,ILB, BANK, CORP, ABS,...
	BOND_TYPE VARCHAR(10) COMMENT 'GOV,ILB, BANK, CORP, ABS,...',
	INIT_INB_INDEX DECIMAL(10,4),
	-- ANNUITY, EQUAL, BULLET
	REDEM_MCD VARCHAR(10) COMMENT 'ANNUITY, EQUAL, BULLET',
	AMORT_ST_DATE VARCHAR(8),
	DEFER_TERM VARCHAR(10),
	AMORT_CNT DECIMAL(10,0),
	AMORT_FREQ DECIMAL(10,0),
	AMORT_AMT DECIMAL(19,4),
	IS_CP_PREPAY CHAR(1),
	REF_IRC_ID VARCHAR(20),
	SPREAD_RATE DECIMAL(10,4),
	REFIXING_TERM VARCHAR(10),
	FIRST_COUPON_DATE VARCHAR(8),
	-- FORWARD, BACKWARD,...
	CP_DATE_GEN VARCHAR(10) COMMENT 'FORWARD, BACKWARD,...',
	REFIXING_SLIDE_NUM DECIMAL(10,0),
	CAP_RATE DECIMAL(10,4),
	FLOOR_RATE DECIMAL(10,4),
	-- CONVERTIBLE, EXCHANGABLE,...
	STOCK_LINKED_TYPE VARCHAR(10) COMMENT 'CONVERTIBLE, EXCHANGABLE,...',
	REF_STOCK_ID VARCHAR(20),
	OPTION_ST_DATE VARCHAR(8),
	OPTION_ED_DATE VARCHAR(8),
	STRIKE_PRICE DECIMAL(10,4),
	CONVERSION_RATIO DECIMAL(10,4),
	PRIMARY KEY (BOND_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BOND_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	BOND_ID VARCHAR(20) NOT NULL,
	BASE_PRICE DECIMAL(19,4),
	OPEN_PRICE DECIMAL(19,4),
	HIGH_PRICE DECIMAL(19,4),
	LOW_PRICE DECIMAL(19,4),
	CLOSE_PRICE DECIMAL(19,4),
	VOLUME DECIMAL(19,0),
	VOLUME_AMT DECIMAL(19,0),
	ADJ_DURATION DECIMAL(19,4),
	MD_DURATION DECIMAL(19,4),
	EFFECT_DURATION DECIMAL(19,4),
	ADJ_CONVEXITY DECIMAL(19,4),
	EFFECT_CONVEXITY DECIMAL(19,4),
	PRIMARY KEY (BSSD, BOND_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BOND_INDEX
(
	BOND_INDEX_ID VARCHAR(20) NOT NULL,
	BOND_INDEX_NAME VARCHAR(50),
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (BOND_INDEX_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BOND_INDEX_DETAIL
(
	BOND_INDEX_ID VARCHAR(20) NOT NULL,
	BOND_ID VARCHAR(20) NOT NULL
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BOND_INDEX_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	BOND_INDEX_ID VARCHAR(20) NOT NULL,
	BASE_PRICE DECIMAL(19,4),
	OPEN_PRICE DECIMAL(19,4),
	HIGH_PRICE DECIMAL(19,4),
	LOW_PRICE DECIMAL(19,4),
	CLOSE_PRICE DECIMAL(19,4),
	VOLUME DECIMAL(19,0),
	VOLUME_AMT DECIMAL(19,0),
	PRIMARY KEY (BSSD, BOND_INDEX_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE COUNTERPARTY
(
	COUNTERPARTY_ID VARCHAR(20) NOT NULL,
	COUNTERPARTY_NAME VARCHAR(50),
	-- GOV, BANK, SECURITIES, ASSET, INSURER, BROKER, ...
	COUNTERPARTY_TYPE VARCHAR(10) COMMENT 'GOV, BANK, SECURITIES, ASSET, INSURER, BROKER, ...',
	CORP_NO VARCHAR(13),
	CREDIT_RATING_CD VARCHAR(10),
	PRIMARY KEY (COUNTERPARTY_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE EMPLOYEE
(
	MEMBER_ID VARCHAR(20) NOT NULL,
	MEMBER_NAME VARCHAR(50),
	-- ADMIN, MANAGER, DEALER, STAFF,...
	MEMBER_TYPE VARCHAR(10) COMMENT 'ADMIN, MANAGER, DEALER, STAFF,...',
	ORG_ID VARCHAR(20),
	PRIMARY KEY (MEMBER_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE ETF
(
	ETF_ID VARCHAR(20) NOT NULL,
	ETF_NAME VARCHAR(50),
	ISIN_CD VARCHAR(20),
	BM_ID VARCHAR(20),
	BM_COEFFICIENT DECIMAL(10,4),
	NUM_PER_CU DECIMAL(10,0),
	NUM_SHARES DECIMAL(10,0),
	TOT_AMT DECIMAL(19,0),
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (ETF_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE ETF_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	ETF_ID VARCHAR(20) NOT NULL,
	BASE_PRICE DECIMAL(19,4),
	OPEN_PRICE DECIMAL(19,4),
	HIGH_PRICE DECIMAL(19,4),
	LOW_PRICE DECIMAL(19,4),
	CLOSE_PRICE DECIMAL(19,4),
	VOLUME DECIMAL(19,0),
	VOLUME_AMT DECIMAL(19,0),
	NAV DECIMAL(19,4),
	PRIMARY KEY (BSSD, ETF_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


-- NUMBER OF HOLDING STOCK FOR 1 CU OF ETF
CREATE TABLE ETF_PDF
(
	BSSD VARCHAR(8) NOT NULL,
	ETF_ID VARCHAR(20) NOT NULL,
	PROD_ID VARCHAR(20) NOT NULL,
	HOLDING_QTY DECIMAL(10,0),
	EVAL_AMT DECIMAL(19,4),
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (BSSD, ETF_ID, PROD_ID)
) ENGINE = InnoDB COMMENT = 'NUMBER OF HOLDING STOCK FOR 1 CU OF ETF' DEFAULT CHARACTER SET utf8;


CREATE TABLE FN_ACCOUNT
(
	ACCOUNT_ID VARCHAR(20) NOT NULL,
	ACCOUNT_NAME VARCHAR(50),
	DEALER_ID VARCHAR(20),
	TOTAL_LIMIT DECIMAL(19,0),
	PRIMARY KEY (ACCOUNT_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE FUTURES
(
	FUTURES_ID VARCHAR(20) NOT NULL,
	ISSUE_DATE VARCHAR(8),
	MATURITY_DATE VARCHAR(8),
	UNDERLYING_ID VARCHAR(20),
	NOTIONAL_AMT DECIMAL(19,0),
	STRIKE_PRICE DECIMAL(10,4),
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (FUTURES_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE FUTURES_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	FUTURES_ID VARCHAR(20) NOT NULL,
	BASE_PRICE DECIMAL(19,4),
	OPEN_PRICE DECIMAL(19,4),
	HIGH_PRICE DECIMAL(19,4),
	LOW_PRICE DECIMAL(19,4),
	CLOSE_PRICE DECIMAL(19,4),
	VOLUME DECIMAL(19,0),
	VOLUME_AMT DECIMAL(19,0),
	DELTA DECIMAL(19,4),
	GAMMA DECIMAL(19,4),
	VEGA DECIMAL(19,4),
	THETA DECIMAL(19,4),
	RHO DECIMAL(19,4),
	PRIMARY KEY (BSSD, FUTURES_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE FX_CASH
(
	FX_ID VARCHAR(20) NOT NULL,
	FX_NAME VARCHAR(50),
	TERM_CURR VARCHAR(3),
	BASE_CURR VARCHAR(3),
	-- FOR YEN TO KRW, FX RATE IS QUOTED BASED ON 100 YEN.
	-- SCALE_FACTOR IS SET TO 100 TO USE THE QUOTE PRICE WITHOUT ANY CONVERSION.
	SCALE_FACTOR DECIMAL(10,4) COMMENT 'FOR YEN TO KRW, FX RATE IS QUOTED BASED ON 100 YEN.
SCALE_FACTOR IS SET TO 100 TO USE THE QUOTE PRICE WITHOUT ANY CONVERSION.',
	PRIMARY KEY (FX_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE FX_RATE_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	FX_ID VARCHAR(20) NOT NULL,
	BASE_PRICE DECIMAL(19,4),
	OPEN_PRICE DECIMAL(19,4),
	HIGH_PRICE DECIMAL(19,4),
	LOW_PRICE DECIMAL(19,4),
	CLOSE_PRICE DECIMAL(19,4),
	VOLUME DECIMAL(19,0),
	VOLUME_AMT DECIMAL(19,0),
	PRIMARY KEY (BSSD, FX_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE INTERATE_RATE_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	IR_ID VARCHAR(20) NOT NULL,
	INT_RATE DECIMAL(10,4),
	PRIMARY KEY (BSSD, IR_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE INTEREST_RATE
(
	IR_ID VARCHAR(20) NOT NULL,
	IR_NAME VARCHAR(50),
	ISSUE_DATE VARCHAR(8),
	MATURITY_DATE VARCHAR(8),
	COMPOUND_MTD VARCHAR(10),
	DAYCOUNT_MTD VARCHAR(10),
	INT_TERM VARCHAR(10),
	COUPON_RATE DECIMAL(10,8),
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (IR_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE IRC_BUCKET_DETAIL
(
	IRC_ID VARCHAR(20) NOT NULL,
	IR_ID VARCHAR(20) NOT NULL,
	PRIMARY KEY (IRC_ID, IR_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE IRC_FUNCTION_DETAIL
(
	IRC_ID VARCHAR(20) NOT NULL,
	REF_IRC_ID VARCHAR(20) NOT NULL,
	COEFFICIENT DECIMAL(10,4),
	POWER DECIMAL(10,4),
	CONSTANT DECIMAL(10,4),
	PRIMARY KEY (IRC_ID, REF_IRC_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE IR_CURVE
(
	IRC_ID VARCHAR(20) NOT NULL,
	IRC_NAME VARCHAR(50),
	-- SLIDING, EXPECTATION, CONSTANT,...
	FORWARDIND_MTD VARCHAR(10) COMMENT 'SLIDING, EXPECTATION, CONSTANT,...',
	-- LINEAR, DIS_LINEAR, LOG_LINEAR, EXPONENTIAL, CONSTANT,
	-- BACKWARD_CON, CUBIC_SPLINE
	INTERPOL_MTD VARCHAR(10) COMMENT 'LINEAR, DIS_LINEAR, LOG_LINEAR, EXPONENTIAL, CONSTANT,
BACKWARD_CON, CUBIC_SPLINE',
	-- BUCKET, CURVE FUNCTION_SUM, CURVE_FUNCTION_MUL
	GENERATION_TYPE VARCHAR(10) COMMENT 'BUCKET, CURVE FUNCTION_SUM, CURVE_FUNCTION_MUL',
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (IRC_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE IR_CURVE_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	-- D01, D07, W01, M01, M02,M03, M06,M09,M12, Y01, Y02,Y03,Y04,Y05,Y07,Y10,Y15,Y20
	MATURITY_ID VARCHAR(10) NOT NULL COMMENT 'D01, D07, W01, M01, M02,M03, M06,M09,M12, Y01, Y02,Y03,Y04,Y05,Y07,Y10,Y15,Y20',
	IRC_ID VARCHAR(20) NOT NULL,
	INT_RATE DECIMAL(10,4),
	PRIMARY KEY (BSSD, MATURITY_ID, IRC_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE MACRO_INDEX
(
	MACRO_INDEX_ID VARCHAR(20) NOT NULL,
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (MACRO_INDEX_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE MACRO_INDEX_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	MACRO_INDEX_ID VARCHAR(20) NOT NULL,
	INDEX_VALUE DECIMAL(19,4),
	PRIMARY KEY (BSSD, MACRO_INDEX_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE MARKET_VARIABLE
(
	MV_ID VARCHAR(20) NOT NULL,
	PRIMARY KEY (MV_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE MATURITY
(
	-- D01, D07, W01, M01, M02,M03, M06,M09,M12, Y01, Y02,Y03,Y04,Y05,Y07,Y10,Y15,Y20
	MATURITY_ID VARCHAR(10) NOT NULL COMMENT 'D01, D07, W01, M01, M02,M03, M06,M09,M12, Y01, Y02,Y03,Y04,Y05,Y07,Y10,Y15,Y20',
	DAY_NUM DECIMAL(10,0),
	PRIMARY KEY (MATURITY_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE OPTIONS
(
	OPTION_ID VARCHAR(20) NOT NULL,
	OPTION_NAME VARCHAR(50),
	-- CALL, PUT 
	OPTION_TYPE VARCHAR(10) COMMENT 'CALL, PUT ',
	-- INDEX, STOCK, BINARY, BARIRRER, ....
	-- 
	EXOTIC_TYPE VARCHAR(10) COMMENT 'INDEX, STOCK, BINARY, BARIRRER, ....
',
	ISSUE_DATE VARCHAR(8),
	MATURITY_DATE VARCHAR(8),
	UNDERLYING_ID VARCHAR(20),
	STRIKE_PRICE DECIMAL(10,4),
	-- MULTIPLIER TO CONVERT PRICE INTO CURRENCY AMOUNT
	MULTIPLIER DECIMAL(10,0) COMMENT 'MULTIPLIER TO CONVERT PRICE INTO CURRENCY AMOUNT',
	-- RATIO TO CHANGE OPTION INTO UNDERLING ASSET
	CONVERSION_RATIO DECIMAL(10,4) COMMENT 'RATIO TO CHANGE OPTION INTO UNDERLING ASSET',
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	ISSUER_ID VARCHAR(20),
	EXCHANGE_ID VARCHAR(20),
	PRIMARY KEY (OPTION_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE OPTION_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	OPTION_ID VARCHAR(20) NOT NULL,
	BASE_PRICE DECIMAL(19,4),
	OPEN_PRICE DECIMAL(19,4),
	HIGH_PRICE DECIMAL(19,4),
	LOW_PRICE DECIMAL(19,4),
	CLOSE_PRICE DECIMAL(19,4),
	VOLUME DECIMAL(19,0),
	VOLUME_AMT DECIMAL(19,0),
	DELTA DECIMAL(19,4),
	GAMMA DECIMAL(19,4),
	VEGA DECIMAL(19,4),
	THETA DECIMAL(19,4),
	RHO DECIMAL(19,4),
	IMPLIED_VOL DECIMAL(19,4),
	UNSETTLE_AMT DECIMAL(19,0),
	PRIMARY KEY (BSSD, OPTION_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE PORTFOLIO
(
	PORT_ID VARCHAR(50) NOT NULL,
	PORT_NAME VARCHAR(50),
	GROUP_ID VARCHAR(20),
	PARENT_PORT_ID VARCHAR(50),
	LEVEL1 VARCHAR(20),
	LEVEL2 VARCHAR(20),
	LEVEL3 VARCHAR(20),
	LEVLE4 VARCHAR(20),
	LEVEL5 VARCHAR(20),
	PRIMARY KEY (PORT_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE PORTFOLIO_CASHFLOW
(
	BSSD VARCHAR(8) NOT NULL,
	PORT_ID VARCHAR(50) NOT NULL,
	CF_DATE VARCHAR(8) NOT NULL,
	CF_AMT DECIMAL(19,4),
	CF_PRIN_AMT DECIMAL(19,4),
	CF_INT_AMT DECIMAL(19,4),
	PRIMARY KEY (BSSD, PORT_ID, CF_DATE)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE PORTFOLIO_DETAIL
(
	BSSD VARCHAR(8) NOT NULL,
	PORT_ID VARCHAR(50) NOT NULL,
	POS_ID VARCHAR(50) NOT NULL,
	PRIMARY KEY (BSSD, PORT_ID, POS_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE PORTFOLIO_RETURN
(
	BSSD VARCHAR(8) NOT NULL,
	PORT_ID VARCHAR(50) NOT NULL,
	BOOK_AMT DECIMAL(19,4),
	PRES_VALUE DECIMAL(19,4),
	DAILY_RETURN DECIMAL(19,4),
	MONTHLY_RETURN DECIMAL(19,4),
	QUARTLY_RETURN DECIMAL(19,4),
	ANNUAL_RETURN DECIMAL(19,4),
	FISCAL_RETURN DECIMAL(19,4),
	DELTA_RETURN DECIMAL(19,4),
	GAMMA_RETURN DECIMAL(19,4),
	VEGA_RETURN DECIMAL(19,4),
	THETA_RETURN DECIMAL(19,4),
	RHO_RETURN DECIMAL(19,4),
	PRIMARY KEY (BSSD, PORT_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE PORTFOLIO_RISK
(
	BSSD VARCHAR(8) NOT NULL,
	PORT_ID VARCHAR(50) NOT NULL,
	VAR_GEN_ID VARCHAR(10) NOT NULL,
	VAR DECIMAL(19,4),
	EXPECTED_SHORTFALL DECIMAL(19,4),
	EQ_VAR DECIMAL(19,4),
	FX_VAR DECIMAL(19,4),
	IR_VAR DECIMAL(19,4),
	CO_VAR DECIMAL(19,4),
	MARGINAL_VAR DECIMAL(19,4),
	CONTRIBUTION_VAR DECIMAL(19,4),
	PRIMARY KEY (BSSD, PORT_ID, VAR_GEN_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE PORTFOLIO_RISK_DETAIL
(
	BSSD VARCHAR(8) NOT NULL,
	PORT_ID VARCHAR(50) NOT NULL,
	-- FOR VAR_TYPE WITH DELTA_NORMAL, DELTA_GAMMA 
	VAR_GEN_ID VARCHAR(10) NOT NULL COMMENT 'FOR VAR_TYPE WITH DELTA_NORMAL, DELTA_GAMMA ',
	RF_ID VARCHAR(20) NOT NULL,
	DELTA_EQUV DECIMAL(19,4),
	GAMMA_EQUV DECIMAL(19,4),
	PRIMARY KEY (BSSD, PORT_ID, VAR_GEN_ID, RF_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE PORTFOLIO_SENSITIVITY
(
	BSSD VARCHAR(8) NOT NULL,
	PORT_ID VARCHAR(50) NOT NULL,
	BOOK_AMT DECIMAL(19,4),
	PRES_VALUE DECIMAL(19,4),
	-- WEIGHTED RESIDUAL MATURITY
	WGT_RES_MATURITY DECIMAL(19,4) COMMENT 'WEIGHTED RESIDUAL MATURITY',
	ADJ_DURATION DECIMAL(19,4),
	MD_DURATION DECIMAL(19,4),
	EFFECT_DURATION DECIMAL(19,4),
	ADJ_CONVEXITY DECIMAL(19,4),
	EFFECT_CONVEXITY DECIMAL(19,4),
	PV01 DECIMAL(19,4),
	PRIMARY KEY (BSSD, PORT_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE POSITION
(
	POS_ID VARCHAR(50) NOT NULL,
	POS_NAME VARCHAR(50),
	PROD_ID VARCHAR(20),
	DEALER_ID VARCHAR(20),
	COUNERTPARTY_ID VARCHAR(20),
	ACCOUNT_ID VARCHAR(20),
	INIT_TX_DATE VARCHAR(8),
	LAST_TX_DATE VARCHAR(8),
	INIT_TX_PRICE DECIMAL(19,4),
	INIT_FEE_AMT DECIMAL(19,4),
	INIT_TX_AMT DECIMAL(19,4),
	HOLDING_QTY DECIMAL(19,4),
	PRIMARY KEY (POS_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE POSITION_CASHFLOW
(
	BSSD VARCHAR(8) NOT NULL,
	POS_ID VARCHAR(50) NOT NULL,
	CF_DATE VARCHAR(8) NOT NULL,
	CF_AMT DECIMAL(19,4),
	CF_PRIN_AMT DECIMAL(19,4),
	CF_INT_AMT DECIMAL(19,4),
	PRIMARY KEY (BSSD, POS_ID, CF_DATE)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE POSITION_GREEKS
(
	BSSD VARCHAR(8) NOT NULL,
	POS_ID VARCHAR(50) NOT NULL,
	UNDERLYING_ID VARCHAR(20) NOT NULL,
	DELTA DECIMAL(19,4),
	GAMMA DECIMAL(19,4),
	VEGA DECIMAL(19,4),
	THETA DECIMAL(19,4),
	RHO DECIMAL(19,4),
	PRIMARY KEY (BSSD, POS_ID, UNDERLYING_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE POSITION_IR_SENSITITIVY
(
	BSSD VARCHAR(8) NOT NULL,
	POS_ID VARCHAR(50) NOT NULL,
	RESIDUAL_MATURITY DECIMAL(10,4),
	ADJ_DURATION DECIMAL(19,4),
	MD_DURATION DECIMAL(19,4),
	EFFECT_DURATION DECIMAL(19,4),
	ADJ_CONVEXITY DECIMAL(19,4),
	EFFECT_CONVEXITY DECIMAL(19,4),
	PRIMARY KEY (BSSD, POS_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE POSITION_RETURN
(
	BSSD VARCHAR(8) NOT NULL,
	POS_ID VARCHAR(50) NOT NULL,
	BOOK_AMT DECIMAL(19,4),
	PV DECIMAL(19,4),
	DAILY_RETURN DECIMAL(19,4),
	MONTHLY_RETURN DECIMAL(19,4),
	QUARTLY_RETURN DECIMAL(19,4),
	ANNUAL_RETURN DECIMAL(19,4),
	FISCAL_RETURN DECIMAL(19,4),
	DELTA_RETURN DECIMAL(19,4),
	GAMMA_RETURN DECIMAL(19,4),
	VEGA_RETURN DECIMAL(19,4),
	THETA_RETURN DECIMAL(19,4),
	RHO_RETURN DECIMAL(19,4),
	PRIMARY KEY (BSSD, POS_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE POSITION_RISK
(
	BSSD VARCHAR(8) NOT NULL,
	POS_ID VARCHAR(50) NOT NULL,
	VAR_GEN_ID VARCHAR(10) NOT NULL,
	VAR DECIMAL(19,4),
	EXPECTED_SHORTFALL DECIMAL(19,4),
	EQ_VAR DECIMAL(19,4),
	FX_VAR DECIMAL(19,4),
	IR_VAR DECIMAL(19,4),
	CO_VAR DECIMAL(19,4),
	MARGINAL_VAR DECIMAL(19,4),
	CONTRIBUTION_VAR DECIMAL(19,4),
	PRIMARY KEY (BSSD, POS_ID, VAR_GEN_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE POSITION_RISK_DETAIL
(
	BSSD VARCHAR(8) NOT NULL,
	POS_ID VARCHAR(50) NOT NULL,
	VAR_GEN_ID VARCHAR(10) NOT NULL,
	RF_ID VARCHAR(20) NOT NULL,
	DELTA_EQUV DECIMAL(19,4),
	GAMMA_EQUV DECIMAL(19,4),
	PRIMARY KEY (BSSD, POS_ID, VAR_GEN_ID, RF_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE PRODUCT_CASHFLOW
(
	BSSD VARCHAR(8) NOT NULL,
	PROD_ID VARCHAR(20) NOT NULL,
	CF_DATE VARCHAR(8) NOT NULL,
	CF_AMT DECIMAL(19,4),
	CF_PRIN_AMT DECIMAL(19,4),
	CF_INT_AMT DECIMAL(19,4),
	PRIMARY KEY (BSSD, PROD_ID, CF_DATE)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE RISK_FACTOR
(
	RF_ID VARCHAR(20) NOT NULL,
	RF_NAME VARCHAR(50),
	-- EQ, IR, FX, CO, VO
	RF_TYPE VARCHAR(10) COMMENT 'EQ, IR, FX, CO, VO',
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (RF_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE SCENARIO
(
	SCENARIO_ID VARCHAR(20) NOT NULL,
	SCENARIO_NAME VARCHAR(50),
	-- UD, HSVAR, MCVAR, ...
	SCENARIO_TYPE VARCHAR(10) COMMENT 'UD, HSVAR, MCVAR, ...',
	SCENARIO_SET VARCHAR(20),
	PRIMARY KEY (SCENARIO_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE SCENARIO_DETAIL
(
	SCENARIO_ID VARCHAR(20) NOT NULL,
	MV_ID VARCHAR(20) NOT NULL,
	-- MULTIPLE, ADD, ...
	SHOCK_TYPE VARCHAR(10) COMMENT 'MULTIPLE, ADD, ...',
	SHOCK_VALUE DECIMAL(19,4),
	PRIMARY KEY (SCENARIO_ID, MV_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE SCENARIO_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	SCENARIO_ID VARCHAR(20) NOT NULL,
	MV_ID VARCHAR(20) NOT NULL,
	BASE_VALUE DECIMAL(19,4),
	SCENARIO_VALUE DECIMAL(19,4),
	PRIMARY KEY (BSSD, SCENARIO_ID, MV_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE SCENARIO_SET
(
	SCENARIO_SET VARCHAR(20) NOT NULL,
	SCENARIO_SET_NAME VARCHAR(50),
	VAR_SCE_ID VARCHAR(20) NOT NULL,
	PRIMARY KEY (SCENARIO_SET)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


-- SETTINGS FOR CONVERTING SCENARIO USED IN VAR CALCULATION  INTO GENERAL FORM.
-- SO IT REQUIRES THE SCENARIO GENERATION PROCEDURE
CREATE TABLE SCENARIO_VAR
(
	VAR_SCE_ID VARCHAR(20) NOT NULL,
	-- HISTORICAL, MC
	VAR_TYPE VARCHAR(10) COMMENT 'HISTORICAL, MC',
	TIME_HORIZON DECIMAL(10,0),
	-- ONLY FOR HISTIORICAL VAR
	-- TO PICK THE HISTORY MARKET DATA BACKWARD FROM THIS DATE  TO GENERATE HISTORICAL SCEANRIO.  IF NULL, PICK THE  HISTORY DATA FROM CURRENT DATA
	SAMPLING_BSSD VARCHAR(8) COMMENT 'ONLY FOR HISTIORICAL VAR
TO PICK THE HISTORY MARKET DATA BACKWARD FROM THIS DATE  TO GENERATE HISTORICAL SCEANRIO.  IF NULL, PICK THE  HISTORY DATA FROM CURRENT DATA',
	SAMPLING_SIZE DECIMAL(10,0),
	-- VARIANCE_COVARIANCE MATRIX FOR MONTE CARLO SIMULATION
	VCV_ID VARCHAR(20) COMMENT 'VARIANCE_COVARIANCE MATRIX FOR MONTE CARLO SIMULATION',
	PRIMARY KEY (VAR_SCE_ID)
) ENGINE = InnoDB COMMENT = 'SETTINGS FOR CONVERTING SCENARIO USED IN VAR CALCULATION  IN' DEFAULT CHARACTER SET utf8;


CREATE TABLE STOCK
(
	STOCK_ID VARCHAR(20) NOT NULL,
	-- NOMARL, PREFERRED, ETF,SPEC, ...
	STOCK_TYPE VARCHAR(10) COMMENT 'NOMARL, PREFERRED, ETF,SPEC, ...',
	-- STOCK EXCHANGE IDENDIFIER
	EXCHANGE_ID VARCHAR(20) COMMENT 'STOCK EXCHANGE IDENDIFIER',
	IS_LISTED CHAR(1),
	NUM_SHARES DECIMAL(10,0),
	ISSUER_ID VARCHAR(30),
	FACE_AMT DECIMAL(10,0),
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (STOCK_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE STOCK_EXCHANGE
(
	EXCHANGE_ID VARCHAR(20) NOT NULL,
	EXCHANGE_NAME VARCHAR(50),
	CURR_CD VARCHAR(3),
	NATION_CD VARCHAR(3),
	PRIMARY KEY (EXCHANGE_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE STOCK_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	STOCK_ID VARCHAR(20) NOT NULL,
	BASE_PRICE DECIMAL(19,4),
	OPEN_PRICE DECIMAL(19,4),
	HIGH_PRICE DECIMAL(19,4),
	LOW_PRICE DECIMAL(19,4),
	CLOSE_PRICE DECIMAL(19,4),
	VOLUME DECIMAL(19,0),
	VOLUME_AMT DECIMAL(19,0),
	DIVIDEN_GAP DECIMAL(19,4),
	PRIMARY KEY (BSSD, STOCK_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE STOCK_INDEX
(
	ST_INDEX_ID VARCHAR(20) NOT NULL,
	ST_INDEX_NAME VARCHAR(50),
	INDEX_TYPE VARCHAR(10),
	PRIMARY KEY (ST_INDEX_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE STOCK_INDEX_DETAIL
(
	ST_INDEX_ID VARCHAR(20) NOT NULL,
	STOCK_ID VARCHAR(20) NOT NULL,
	INIT_WEIGHT DECIMAL(10,4),
	INIT_AMT DECIMAL(19,0),
	IS_ACTIVE CHAR(1),
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (ST_INDEX_ID, STOCK_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE STOCK_INDEX_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	ST_INDEX_ID VARCHAR(20) NOT NULL,
	BASE_PRICE DECIMAL(19,4),
	OPEN_PRICE DECIMAL(19,4),
	HIGH_PRICE DECIMAL(19,4),
	LOW_PRICE DECIMAL(19,4),
	CLOSE_PRICE DECIMAL(19,4),
	VOLUME DECIMAL(19,0),
	VOLUME_AMT DECIMAL(19,0),
	PRIMARY KEY (BSSD, ST_INDEX_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE SYNTHETICS
(
	SYN_PROD_ID VARCHAR(20) NOT NULL,
	-- SWAP, SPREAD, USER_DEFINED
	SYNTHETIC_TYPE VARCHAR(10) COMMENT 'SWAP, SPREAD, USER_DEFINED',
	SYN_PROD_NAME VARCHAR(50),
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (SYN_PROD_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE SYNTHETICS_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	SYN_PROD_ID VARCHAR(20) NOT NULL,
	BASE_PRICE DECIMAL(19,4),
	OPEN_PRICE DECIMAL(19,4),
	HIGH_PRICE DECIMAL(19,4),
	LOW_PRICE DECIMAL(19,4),
	CLOSE_PRICE DECIMAL(19,4),
	VOLUME DECIMAL(19,0),
	VOLUME_AMT DECIMAL(19,0),
	PRIMARY KEY (BSSD, SYN_PROD_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE SYNTHETIC_DETAIL
(
	SYN_PROD_ID VARCHAR(20) NOT NULL,
	SUB_PROD_ID VARCHAR(20) NOT NULL,
	SUB_PROD_NAME VARCHAR(50),
	WEIGHT DECIMAL(19,4),
	SOURCE_TABLE VARCHAR(50),
	USER_NAME VARCHAR(20),
	LAST_UPDATED VARCHAR(8),
	VERSION_NO DECIMAL,
	PRIMARY KEY (SYN_PROD_ID, SUB_PROD_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE VAR_GEN_MCD
(
	VAR_GEN_ID VARCHAR(10) NOT NULL,
	-- DELTA_NORMAL, DELTA_GAMMA, HISTORICAL, MC
	VAR_TYPE VARCHAR(10) COMMENT 'DELTA_NORMAL, DELTA_GAMMA, HISTORICAL, MC',
	TIME_HORIZON DECIMAL(10),
	PRIMARY KEY (VAR_GEN_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE VCV_MATRIX
(
	VCV_ID VARCHAR(20) NOT NULL,
	-- EWMA, SMA, GARCH...
	VCV_TYPE VARCHAR(10) COMMENT 'EWMA, SMA, GARCH...',
	SAMPLING_SIZE DECIMAL(10,0),
	TIME_HORIZON DECIMAL(10),
	DECAY_FACTOR DECIMAL(10,4),
	-- APPLY ZERO MEAN EVEN THOUGH THE CALCUALTED MEAN OF SAMPLING DATA IS NOT ZERO
	ZERO_MEAN CHAR(1) DEFAULT 'Y' COMMENT 'APPLY ZERO MEAN EVEN THOUGH THE CALCUALTED MEAN OF SAMPLING DATA IS NOT ZERO',
	-- FOR IR TYPE MARKET VARIABLE, THIS FLAG INDICATES THAT THE RAW DATA IS USED FOR ESTIMATION OR CONVERTED DISCOUNT FACTOR IS USED
	PRICE_VOL CHAR(1) DEFAULT 'Y' COMMENT 'FOR IR TYPE MARKET VARIABLE, THIS FLAG INDICATES THAT THE RAW DATA IS USED FOR ESTIMATION OR CONVERTED DISCOUNT FACTOR IS USED',
	PRIMARY KEY (VCV_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE VCV_MATRIX_HIS
(
	BSSD VARCHAR(8) NOT NULL,
	VCV_ID VARCHAR(20) NOT NULL,
	RF_ID VARCHAR(20) NOT NULL,
	REF_RF_ID VARCHAR(20) NOT NULL,
	COVARIANCE DECIMAL(19,4),
	CORREL DECIMAL(10,4),
	PRIMARY KEY (BSSD, VCV_ID, RF_ID, REF_RF_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE VOL_CURVE
(
	VOL_CURVE_ID VARCHAR(20) NOT NULL,
	VOL_CURVE_NAME VARCHAR(50),
	UNDERLYING_ID VARCHAR(20),
	PRIMARY KEY (VOL_CURVE_ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;



/* Create Foreign Keys */

ALTER TABLE BOND_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BOND_INDEX_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ETF_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ETF_PDF
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE FUTURES_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE FX_RATE_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE INTERATE_RATE_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE IR_CURVE_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MACRO_INDEX_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE OPTION_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_CASHFLOW
	ADD FOREIGN KEY (CF_DATE)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_CASHFLOW
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_DETAIL
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_RETURN
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_RISK
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_RISK_DETAIL
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_SENSITIVITY
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_CASHFLOW
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_CASHFLOW
	ADD FOREIGN KEY (CF_DATE)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_GREEKS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_IR_SENSITITIVY
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_RETURN
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_RISK
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PRODUCT_CASHFLOW
	ADD FOREIGN KEY (CF_DATE)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PRODUCT_CASHFLOW
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SCENARIO_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE STOCK_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE STOCK_INDEX_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SYNTHETICS_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VCV_MATRIX_HIS
	ADD FOREIGN KEY (BSSD)
	REFERENCES BASEDATE (BSSD)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BIZUNIT
	ADD FOREIGN KEY (PARENT_ORG_ID)
	REFERENCES BIZUNIT (ORG_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE EMPLOYEE
	ADD FOREIGN KEY (ORG_ID)
	REFERENCES BIZUNIT (ORG_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BOND_HIS
	ADD FOREIGN KEY (BOND_ID)
	REFERENCES BOND (BOND_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BOND_INDEX_DETAIL
	ADD FOREIGN KEY (BOND_ID)
	REFERENCES BOND (BOND_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BOND_INDEX_DETAIL
	ADD FOREIGN KEY (BOND_INDEX_ID)
	REFERENCES BOND_INDEX (BOND_INDEX_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BOND_INDEX_HIS
	ADD FOREIGN KEY (BOND_INDEX_ID)
	REFERENCES BOND_INDEX (BOND_INDEX_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION
	ADD FOREIGN KEY (COUNERTPARTY_ID)
	REFERENCES COUNTERPARTY (COUNTERPARTY_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION
	ADD FOREIGN KEY (DEALER_ID)
	REFERENCES EMPLOYEE (MEMBER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ETF_HIS
	ADD FOREIGN KEY (ETF_ID)
	REFERENCES ETF (ETF_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ETF_PDF
	ADD FOREIGN KEY (ETF_ID)
	REFERENCES ETF (ETF_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION
	ADD FOREIGN KEY (ACCOUNT_ID)
	REFERENCES FN_ACCOUNT (ACCOUNT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE FUTURES_HIS
	ADD FOREIGN KEY (FUTURES_ID)
	REFERENCES FUTURES (FUTURES_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE FX_RATE_HIS
	ADD FOREIGN KEY (FX_ID)
	REFERENCES FX_CASH (FX_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE INTERATE_RATE_HIS
	ADD FOREIGN KEY (IR_ID)
	REFERENCES INTEREST_RATE (IR_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE IRC_BUCKET_DETAIL
	ADD FOREIGN KEY (IR_ID)
	REFERENCES INTEREST_RATE (IR_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE IRC_BUCKET_DETAIL
	ADD FOREIGN KEY (IRC_ID)
	REFERENCES IR_CURVE (IRC_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE IRC_FUNCTION_DETAIL
	ADD FOREIGN KEY (IRC_ID)
	REFERENCES IR_CURVE (IRC_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE IRC_FUNCTION_DETAIL
	ADD FOREIGN KEY (REF_IRC_ID)
	REFERENCES IR_CURVE (IRC_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE IR_CURVE_HIS
	ADD FOREIGN KEY (IRC_ID)
	REFERENCES IR_CURVE (IRC_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MACRO_INDEX_HIS
	ADD FOREIGN KEY (MACRO_INDEX_ID)
	REFERENCES MACRO_INDEX (MACRO_INDEX_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BOND
	ADD FOREIGN KEY (BOND_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BOND_INDEX
	ADD FOREIGN KEY (BOND_INDEX_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ETF
	ADD FOREIGN KEY (ETF_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ETF_PDF
	ADD FOREIGN KEY (PROD_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE FUTURES
	ADD FOREIGN KEY (UNDERLYING_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE FUTURES
	ADD FOREIGN KEY (FUTURES_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE FX_CASH
	ADD FOREIGN KEY (FX_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE INTEREST_RATE
	ADD FOREIGN KEY (IR_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MACRO_INDEX
	ADD FOREIGN KEY (MACRO_INDEX_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE OPTIONS
	ADD FOREIGN KEY (OPTION_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE OPTIONS
	ADD FOREIGN KEY (UNDERLYING_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION
	ADD FOREIGN KEY (PROD_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_GREEKS
	ADD FOREIGN KEY (UNDERLYING_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PRODUCT_CASHFLOW
	ADD FOREIGN KEY (PROD_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE RISK_FACTOR
	ADD FOREIGN KEY (RF_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SCENARIO_DETAIL
	ADD FOREIGN KEY (MV_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE STOCK
	ADD FOREIGN KEY (STOCK_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE STOCK_INDEX
	ADD FOREIGN KEY (ST_INDEX_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SYNTHETICS
	ADD FOREIGN KEY (SYN_PROD_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SYNTHETIC_DETAIL
	ADD FOREIGN KEY (SUB_PROD_ID)
	REFERENCES MARKET_VARIABLE (MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE IR_CURVE_HIS
	ADD FOREIGN KEY (MATURITY_ID)
	REFERENCES MATURITY (MATURITY_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE OPTION_HIS
	ADD FOREIGN KEY (OPTION_ID)
	REFERENCES OPTIONS (OPTION_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO
	ADD FOREIGN KEY (PARENT_PORT_ID)
	REFERENCES PORTFOLIO (PORT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_CASHFLOW
	ADD FOREIGN KEY (PORT_ID)
	REFERENCES PORTFOLIO (PORT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_DETAIL
	ADD FOREIGN KEY (PORT_ID)
	REFERENCES PORTFOLIO (PORT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_RETURN
	ADD FOREIGN KEY (PORT_ID)
	REFERENCES PORTFOLIO (PORT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_RISK
	ADD FOREIGN KEY (PORT_ID)
	REFERENCES PORTFOLIO (PORT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_RISK_DETAIL
	ADD FOREIGN KEY (PORT_ID)
	REFERENCES PORTFOLIO (PORT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_SENSITIVITY
	ADD FOREIGN KEY (PORT_ID)
	REFERENCES PORTFOLIO (PORT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_DETAIL
	ADD FOREIGN KEY (POS_ID)
	REFERENCES POSITION (POS_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_CASHFLOW
	ADD FOREIGN KEY (POS_ID)
	REFERENCES POSITION (POS_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_GREEKS
	ADD FOREIGN KEY (POS_ID)
	REFERENCES POSITION (POS_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_IR_SENSITITIVY
	ADD FOREIGN KEY (POS_ID)
	REFERENCES POSITION (POS_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_RETURN
	ADD FOREIGN KEY (POS_ID)
	REFERENCES POSITION (POS_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_RISK
	ADD FOREIGN KEY (POS_ID)
	REFERENCES POSITION (POS_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_RISK_DETAIL
	ADD FOREIGN KEY (BSSD, POS_ID, VAR_GEN_ID)
	REFERENCES POSITION_RISK (BSSD, POS_ID, VAR_GEN_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_RISK_DETAIL
	ADD FOREIGN KEY (RF_ID)
	REFERENCES RISK_FACTOR (RF_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_RISK_DETAIL
	ADD FOREIGN KEY (RF_ID)
	REFERENCES RISK_FACTOR (RF_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VCV_MATRIX_HIS
	ADD FOREIGN KEY (REF_RF_ID)
	REFERENCES RISK_FACTOR (RF_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VCV_MATRIX_HIS
	ADD FOREIGN KEY (RF_ID)
	REFERENCES RISK_FACTOR (RF_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SCENARIO_DETAIL
	ADD FOREIGN KEY (SCENARIO_ID)
	REFERENCES SCENARIO (SCENARIO_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SCENARIO_HIS
	ADD FOREIGN KEY (SCENARIO_ID, MV_ID)
	REFERENCES SCENARIO_DETAIL (SCENARIO_ID, MV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SCENARIO
	ADD FOREIGN KEY (SCENARIO_SET)
	REFERENCES SCENARIO_SET (SCENARIO_SET)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SCENARIO_SET
	ADD FOREIGN KEY (VAR_SCE_ID)
	REFERENCES SCENARIO_VAR (VAR_SCE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE STOCK_HIS
	ADD FOREIGN KEY (STOCK_ID)
	REFERENCES STOCK (STOCK_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE STOCK_INDEX_DETAIL
	ADD FOREIGN KEY (STOCK_ID)
	REFERENCES STOCK (STOCK_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ETF
	ADD FOREIGN KEY (BM_ID)
	REFERENCES STOCK_INDEX (ST_INDEX_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE STOCK_INDEX_DETAIL
	ADD FOREIGN KEY (ST_INDEX_ID)
	REFERENCES STOCK_INDEX (ST_INDEX_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE STOCK_INDEX_HIS
	ADD FOREIGN KEY (ST_INDEX_ID)
	REFERENCES STOCK_INDEX (ST_INDEX_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SYNTHETICS_HIS
	ADD FOREIGN KEY (SYN_PROD_ID)
	REFERENCES SYNTHETICS (SYN_PROD_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SYNTHETIC_DETAIL
	ADD FOREIGN KEY (SYN_PROD_ID)
	REFERENCES SYNTHETICS (SYN_PROD_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_RISK
	ADD FOREIGN KEY (VAR_GEN_ID)
	REFERENCES VAR_GEN_MCD (VAR_GEN_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PORTFOLIO_RISK_DETAIL
	ADD FOREIGN KEY (VAR_GEN_ID)
	REFERENCES VAR_GEN_MCD (VAR_GEN_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POSITION_RISK
	ADD FOREIGN KEY (VAR_GEN_ID)
	REFERENCES VAR_GEN_MCD (VAR_GEN_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SCENARIO_VAR
	ADD FOREIGN KEY (VCV_ID)
	REFERENCES VCV_MATRIX (VCV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VCV_MATRIX_HIS
	ADD FOREIGN KEY (VCV_ID)
	REFERENCES VCV_MATRIX (VCV_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



