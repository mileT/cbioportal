package org.mskcc.cgds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.mskcc.cgds.model.ClinicalFreeForm;
import org.mskcc.cgds.model.ClinicalParameterMap;

/**
 * Data access object for Clinical Free Form Data.
 */
public class DaoClinicalFreeForm {

    /**
     * Add a new Datum.
     */
    public int addDatum(int cancerStudyId, String caseId, String paramName, String paramValue)
            throws DaoException {
        if (caseId == null || caseId.trim().length() == 0) {
            throw new IllegalArgumentException ("Case ID is null or empty");
        }
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
                con = JdbcUtil.getDbConnection();
                pstmt = con.prepareStatement
                        ("INSERT INTO clinical_free_form (`CANCER_STUDY_ID`, `CASE_ID`, " +
                                "`PARAM_NAME`, `PARAM_VALUE`)" +
                                " VALUES (?,?,?,?)");
                pstmt.setInt(1, cancerStudyId);
                pstmt.setString(2, caseId);
                pstmt.setString(3, paramName);
                pstmt.setString(4, paramValue);
                int rows = pstmt.executeUpdate();
                return rows;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.closeAll(con, pstmt, rs);
        }
    }

    /**
     * Get a slice of clinical data.
     */
    public ClinicalParameterMap getDataSlice(int cancerStudyId, String paramName) throws DaoException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashMap<String, String> valueMap = new HashMap<String, String>();
        try {
            con = JdbcUtil.getDbConnection();
            pstmt = con.prepareStatement ("SELECT * FROM clinical_free_form WHERE CANCER_STUDY_ID=? AND PARAM_NAME=?");
            pstmt.setInt(1, cancerStudyId);
            pstmt.setString(2, paramName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String caseId = rs.getString("CASE_ID");
                String paramValue = rs.getString("PARAM_VALUE");
                valueMap.put(caseId, paramValue);
            }
            return new ClinicalParameterMap(paramName, valueMap);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.closeAll(con, pstmt, rs);
        }
    }

    /**
     * Get all distinct parameters associated with the specified cancer study.
     */
    public HashSet<String> getDistinctParameters (int cancerStudyId) throws DaoException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashSet<String> paramSet = new HashSet<String>();
        try {
            con = JdbcUtil.getDbConnection();
            pstmt = con.prepareStatement ("SELECT DISTINCT(PARAM_NAME) FROM `clinical_free_form`" +
                    "WHERE CANCER_STUDY_ID=?");
            pstmt.setInt(1, cancerStudyId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String paramValue = rs.getString(1);
                paramSet.add(paramValue);
            }
            return paramSet;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.closeAll(con, pstmt, rs);
        }
    }

    /**
     * Get all cases (case IDs) associated with the given cancer study ID.
     */
    public HashSet<String> getAllCases (int cancerStudyId) throws DaoException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashSet<String> caseSet = new HashSet<String>();
        try {
            con = JdbcUtil.getDbConnection();
            pstmt = con.prepareStatement ("SELECT DISTINCT(CASE_ID) FROM `clinical_free_form`" +
                    "WHERE CANCER_STUDY_ID=?");
            pstmt.setInt(1, cancerStudyId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String paramValue = rs.getString(1);
                caseSet.add(paramValue);
            }
            return caseSet;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.closeAll(con, pstmt, rs);
        }
    }

    /**
     * Retrieves all rows in the clinical_free_form table for the specified cancer study,
     * and returns a list of ClinicalFreeForm instances where each instance represents
     * a single row in the table.
     * 
     * @param cancerStudyId	internal id of a specific cancer study
     * @return				list of all ClinicalFreeForm instances for the given cancer study id
     */
    public List<ClinicalFreeForm> getCasesByCancerStudy(int cancerStudyId) throws DaoException
    {
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            con = JdbcUtil.getDbConnection();
            pstmt = con.prepareStatement ("SELECT * FROM `clinical_free_form`" +
                    "WHERE CANCER_STUDY_ID=?");
            pstmt.setInt(1, cancerStudyId);
            rs = pstmt.executeQuery();
            
            ArrayList<ClinicalFreeForm> dataList = new ArrayList<ClinicalFreeForm>();
            
            while (rs.next())
            {
            	// get all values as String
                String caseId = rs.getString("CASE_ID");
                String paramName = rs.getString("PARAM_NAME");
                String paramValue = rs.getString("PARAM_VALUE");
                
                // create new ClinicalFreeForm instance
                ClinicalFreeForm data = new ClinicalFreeForm(cancerStudyId,
                	caseId,
                	paramName,
                	paramValue);
                
                // add it to the list
                dataList.add(data);
            }
            
            return dataList;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.closeAll(con, pstmt, rs);
        }
    }

    /**
     * Retrieves all rows in the clinical_free_form table for the specified cases,
     * and returns a list of ClinicalFreeForm instances where each instance represents
     * a single row in the table.
     * 
     * @param caseIds
     * @return list of all ClinicalFreeForm instances for the given cancer study id
     */
    public List<ClinicalFreeForm> getCasesByCases(Collection<String> caseIds) throws DaoException
    {
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            con = JdbcUtil.getDbConnection();
            pstmt = con.prepareStatement ("SELECT * FROM `clinical_free_form`" +
                    "WHERE CASE_ID IN('"
                    + StringUtils.join(caseIds,"','") +"')");
            rs = pstmt.executeQuery();
            
            ArrayList<ClinicalFreeForm> dataList = new ArrayList<ClinicalFreeForm>();
            
            while (rs.next())
            {
            	// get all values as String
                int cancerStudyId = rs.getInt("CANCER_STUDY_ID");
                String caseId = rs.getString("CASE_ID");
                String paramName = rs.getString("PARAM_NAME");
                String paramValue = rs.getString("PARAM_VALUE");
                
                // create new ClinicalFreeForm instance
                ClinicalFreeForm data = new ClinicalFreeForm(cancerStudyId,
                	caseId,
                	paramName,
                	paramValue);
                
                // add it to the list
                dataList.add(data);
            }
            
            return dataList;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.closeAll(con, pstmt, rs);
        }
    }
    
    /**
     * Deletes all Records.
     * @throws org.mskcc.cgds.dao.DaoException DAO Error.
     */
    public void deleteAllRecords() throws DaoException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getDbConnection();
            pstmt = con.prepareStatement("TRUNCATE TABLE clinical_free_form");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.closeAll(con, pstmt, rs);
        }
    }
}