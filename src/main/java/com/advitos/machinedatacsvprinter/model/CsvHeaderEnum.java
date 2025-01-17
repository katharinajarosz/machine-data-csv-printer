package com.advitos.machinedatacsvprinter.model;

public enum CsvHeaderEnum {
    TIMESTAMP,
    MACHINE_ID,
    SYSTEM_MAIN_STATE,
    S_P_HW_ACID_CD,
    S_P_HW_BASE_CD,
    S_P_DIA_OUT_CD,
    S_P_DIA_IN_CD,
    S_P_BLOOD_ART_CD,
    S_P_BLOOD_PRE_CD,
    S_P_BLOOD_VENOUS_CD,
    S_P_BLOOD_ART_MON_CD,
    S_T_HOUSING_CD,
    S_T_EXTERN_CD,
    S_SCALE_1_CD,
    S_SCALE_2_CD,
    S_SCALE_SUM_CD,
    S_VEN_CLAMP_POSITION_CD,
    S_ART_BLOOD_CD,
    S_BLOOD_PUMP_FB_CD,
    S_PRE_DILUTION_PUMP_FB_CD,
    S_GP_HW_ACID_FB_CD,
    S_GP_HW_BASE_FB_CD,
    S_GP_DIA_FB_CD,
    S_PP_CONC_ACID_FB_CD,
    S_PP_CONC_BASE_FB_CD,
    S_PP_SU_ACID_FB_CD,
    S_PP_SU_BASE_FB_CD,
    S_PP_WA_A_FB_CD,
    S_PP_WA_B_FB_CD,
    S_F_DIA_CD,
    S_F_HW_ACID_CD,
    S_F_HW_BASE_CD,
    S_F_RO_WATER_CD,
    S_BLD_CD,
    S_GP_HW_ACID_CNT_CD,
    S_GP_HW_BASE_CNT_CD,
    S_GP_DIA_CNT_CD,
    S_BLOOD_PUMP_CNT_CD,
    S_PRE_DILUTION_PUMP_CNT_CD,
    S_PP_SU_ACID_CNT_CD,
    S_PP_SU_BASE_CNT_CD,
    S_PP_WA_A_CNT_CD,
    S_PP_WA_B_CNT_CD,
    S_PP_CONC_ACID_CNT_CD,
    S_PP_CONC_BASE_CNT_CD,
    S_CONT_MOTOR_FB_CD,
    S_CONTROL_BOARD_TEMPERATURE_CD,
    C_T_ACID_CD,
    C_T_BASE_CD,
    C_T_MON_CD,
    C_T_RES_CD,
    C_PH_ACID_CD,
    C_PH_BASE_CD,
    C_PH_MON_CD,
    C_PH_RES_CD,
    C_TMP_CD,
    C_F_SU_BASE_CD,
    C_P_SYSTEM_CD,
    C_UF_VOLUME_CD,
    C_PRE_VOLUME_CD,
    C_RO_VOLUME_FLOW_CD,
    C_RO_VOLUME_CD,
    C_WASTE_VOLUME_CD,
    C_ACID_VOLUME_CD,
    C_BASE_VOLUME_CD,
    C_BLOOD_VOLUME_CD,
    C_EFF_BLOOD_VOLUME_CD,
    C_GP_HW_ACID_OPERATING_HOURS_CD,
    C_GP_HW_ACID_OPERATING_CYCLES_CD,
    C_GP_HW_BASE_OPERATING_HOURS_CD,
    C_GP_HW_BASE_OPERATING_CYCLES_CD,
    C_GP_DIA_OPERATING_HOURS_CD,
    C_GP_DIA_OPERATING_CYCLES_CD,
    C_BLOOD_PUMP_OPERATING_HOURS_CD,
    C_BLOOD_PUMP_OPERATING_CYCLES_CD,
    C_PRE_DILUTION_PUMP_OPERATING_HOURS_CD,
    C_PRE_DILUTION_PUMP_OPERATING_CYCLES_CD,
    C_PP_SU_ACID_OPERATING_HOURS_CD,
    C_PP_SU_ACID_OPERATING_CYCLES_CD,
    C_PP_SU_BASE_OPERATING_HOURS_CD,
    C_PP_SU_BASE_OPERATING_CYCLES_CD,
    C_PP_WA_A_OPERATING_HOURS_CD,
    C_PP_WA_A_OPERATING_CYCLES_CD,
    C_PP_WA_B_OPERATING_HOURS_CD,
    C_PP_WA_B_OPERATING_CYCLES_CD,
    C_PP_CONC_ACID_OPERATING_HOURS_CD,
    C_PP_CONC_ACID_OPERATING_CYCLES_CD,
    C_PP_CONC_BASE_OPERATING_HOURS_CD,
    C_PP_CONC_BASE_OPERATING_CYCLES_CD,
    C_SUPPLY_VOLUME_CD,
    S_SERVICE_SWITCH_CD,
    S_DOOR_BLOOD_PUMP_CD,
    S_DOOR_PRE_DILUTION_PUMP_CD,
    S_BS_AIR_PRE_CD,
    S_BS_AIR_CONC_ACID_CD,
    S_BS_AIR_CONC_BASE_CD,
    S_OVERPRESSURE_WASTE_CD,
    S_VEN_BUBBLE_1_CD,
    S_FCC_1_CD,
    S_FCC_2_CD,
    S_HEATER_HW_FB_CD,
    S_HEATER_BASE_FB_CD,
    S_HEATER_SAFETY_RELAIS_FB_CD,
    S_ALARM_PROTECT_CD,
    S_HANSEN_1_CD,
    S_HANSEN_2_CD,
    C_PATIENT_CONNECTED_CD,
    S_CONT_MOTOR_UP_CD,
    S_CONT_MOTOR_DOWN_CD,
    S_BS_RES_CROSS_0_CD,
    S_BS_RES_CROSS_1_CD,
    RT_ACTIVE_CD,
    CL_FLOW_PP_SU_ACID_HMI,
    CL_FLOW_PP_SU_ACID_DEM,
    CL_FLOW_PP_SU_BASE_HMI,
    CL_FLOW_PP_SU_BASE_DEM,
    CL_FLOW_PP_CONC_ACID_HMI,
    CL_FLOW_PP_CONC_ACID_DEM,
    CL_FLOW_PP_CONC_BASE_HMI,
    CL_FLOW_PP_CONC_BASE_DEM,
    CL_FLOW_BLOOD_PUMP_HMI,
    CL_FLOW_BLOOD_PUMP_DEM,
    CL_FLOW_PRE_DILUTION_PUMP_HMI,
    CL_FLOW_PRE_DILUTION_PUMP_DEM,
    CL_FLOW_UF_PUMP_HMI,
    CL_FLOW_UF_PUMP_DEM,
    CL_FLOW_DIA_HMI,
    CL_FLOW_DIA_DEM,
    CL_FLOW_HW_ACID_HMI,
    CL_FLOW_HW_ACID_DEM,
    CL_FLOW_HW_BASE_HMI,
    CL_FLOW_HW_BASE_DEM,
    CL_SPEED_DIA_HMI,
    CL_SPEED_DIA_DEM,
    CL_SPEED_HW_ACID_HMI,
    CL_SPEED_HW_ACID_DEM,
    CL_SPEED_HW_BASE_HMI,
    CL_SPEED_HW_BASE_DEM,
    CL_FLOW_PP_WA_A_HMI,
    CL_FLOW_PP_WA_A_DEM,
    CL_FLOW_PP_WA_B_HMI,
    CL_FLOW_PP_WA_B_DEM,
    CL_BALANCE_PP_WASTE_HMI,
    CL_BALANCE_PP_WASTE_DEM,
    CL_PRESSURE_PP_WASTE_HMI,
    CL_PRESSURE_PP_WASTE_DEM,
    CL_TREATMENT_PRESSURE_PP_WASTE_HMI,
    CL_TREATMENT_PRESSURE_PP_WASTE_DEM,
    CL_PERCENTAGE_HEATER_HW_HMI,
    CL_PERCENTAGE_HEATER_HW_DEM,
    CL_PERCENTAGE_HEATER_BASE_HMI,
    CL_PERCENTAGE_HEATER_BASE_DEM,
    CL_HEATER_HW_HMI,
    CL_HEATER_HW_DEM,
    CL_HEATER_BASE_HMI,
    CL_HEATER_BASE_DEM,
    CL_SUPPLY_FLOW_HMI,
    CL_SUPPLY_FLOW_DEM,
    CL_SUPPLY_REL_HMI,
    CL_SUPPLY_REL_DEM,
    CL_PH_ACID_HMI,
    CL_PH_ACID_DEM,
    CL_PH_BASE_HMI,
    CL_PH_BASE_DEM,
    CL_PH_RES_HMI,
    CL_PH_RES_DEM,
    A_BLOOD_PUMP_CD,
    A_PRE_DILUTION_PUMP_CD,
    A_GP_DIA_CD,
    A_GP_HW_ACID_CD,
    A_GP_HW_BASE_CD,
    A_PP_SU_ACID_CD,
    A_PP_SU_BASE_CD,
    A_PP_WA_A_CD,
    A_PP_WA_B_CD,
    A_PP_CONC_ACID_CD,
    A_PP_CONC_BASE_CD,
    A_HEATER_HW_CD,
    A_HEATER_BASE_CD,
    A_CLAMP_VEN_HMI,
    A_INFO_GREEN_HMI,
    A_INFO_YELLOW_HMI,
    A_INFO_RED_HMI,
    A_ART_BLOOD_HMI,
    A_BLD_HMI

}
