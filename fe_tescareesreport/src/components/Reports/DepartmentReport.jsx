import classes from "./DepartmentReport.css";
import React, { useEffect, useState } from "react";
import { Form } from "react-bootstrap";
import { ToastContainer, toast } from "react-toastify";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import api from "../../ApiRequest/axios";
import ChartDepartmentReport from "./ChartDepartReport";

const DepartmentReport = () => {
  const [departments, setDepartments] = useState([]);
  const [departmentSelect, setDepartmentSelect] = useState(1);
  const [departmentReport, setDepartmentReport] = useState(null);

  const [dataChartGeneral, setDataChartGeneral] = useState([]);
  const [dataChartProfile, setDataChartProfile] = useState([]);
  const [dataChartPassProfile, setDataChartPassProfile] = useState([]);

  const getDepartment = async () => {
    const res = await api.post("/department");
    return res;
  };

  const handleReport = async () => {
    await api.post(`report/department/${departmentSelect}`).then((res) => {
      setDepartmentReport(res.data);

      setDataChartGeneral([
        { y: res.data.hiredCandidatesRate * 100, label: "Đậu" },
        {
          y: res.data.rejectedCandidatesRate * 100,
          label: "Bị loại",
        },
      ]);
      setDataChartProfile([
        {
          y: res.data.passingProfileRate * 100,
          label: "Hồ sơ đậu yêu cầu",
        },
        {
          y: res.data.failProfileRate * 100,
          label: "Hồ sơ không đạt yêu cầu",
        },
      ]);
      setDataChartPassProfile([
        {
          y: res.data.hiredCandidatePassProfRate * 100,
          label: "Đậu theo yêu cầu",
        },
        {
          y: res.data.rejectedCandidatePassProfRate * 100,
          label: "Bị loại theo yêu cầu",
        },
      ]);

      console.log(departmentReport);
    });
  };

  useEffect(() => {
    getDepartment().then((res) => {
      console.log(res);
      setDepartments(res.data);
      console.log(departments);
    });
  }, []);

  return (
    <div className="">
      <div className="select-department">
        <div className="title">Phòng ban thống kê:</div>
        <div>
          <Form>
            <Form.Group controlId="departmentSelect">
              <Form.Control
                className="select-department-detail"
                as="select"
                value={departmentSelect}
                onChange={(e) => setDepartmentSelect(e.target.value)}
                required
              >
                {departments.map((department) => (
                  <option
                    key={department.departmentId}
                    value={department.departmentId}
                  >
                    {department.departmentName}
                  </option>
                ))}
              </Form.Control>
            </Form.Group>
          </Form>
        </div>
        <div className="button-report">
          <button onClick={handleReport}>Thống kê</button>
        </div>
      </div>
      {dataChartGeneral.length > 0 && (
        <div className="chart">
          <ChartDepartmentReport
            data={dataChartGeneral}
            title={"Tổng quan tỉ lệ tuyển dụng"}
          />
        </div>
      )}
      {dataChartProfile.length > 0 && (
        <div className="chart">
          <ChartDepartmentReport
            data={dataChartProfile}
            title={"Tỷ lệ hồ sơ theo yêu cầu phòng ban"}
          />
        </div>
      )}
      {dataChartPassProfile.length > 0 && (
        <div className="chart">
          <ChartDepartmentReport
            data={dataChartPassProfile}
            title={"Tỷ lệ tuyển dụng theo hồ sơ đạt yêu cầu"}
          />
        </div>
      )}

      {departmentReport !== null && (
        <div>
          <div className="table-department-report">
            <div className="title-table">
              <p>Bảng thống kê</p>
            </div>
            <TableContainer component={Paper}>
              <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell align="center">
                      Tổng số ứng viên ứng tuyển
                    </TableCell>
                    <TableCell align="center">
                      Số ứng viên đạt yêu cầu
                    </TableCell>
                    <TableCell align="center">
                      Số ứng viên không đạt yêu cầu
                    </TableCell>
                    <TableCell align="center">Số ứng viên được tuyển</TableCell>
                    <TableCell align="center">
                      Số ứng viên đạt yêu cầu bị loại
                    </TableCell>
                    <TableCell align="center">
                      Tổng số ứng viên bị loại
                    </TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  <TableRow
                    sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                  >
                    <TableCell align="center">
                      {departmentReport.numberOfApplicants}
                    </TableCell>
                    <TableCell align="center">
                      {departmentReport.numberOfPassingProfile}
                    </TableCell>
                    <TableCell align="center">
                      {departmentReport.numberOfFailProfile}
                    </TableCell>

                    <TableCell align="center">
                      {departmentReport.numberOfHiredCandidates}
                    </TableCell>
                    <TableCell align="center">
                      {departmentReport.numberOfRejectedCandidates -
                        departmentReport.numberOfFailProfile}
                    </TableCell>
                    <TableCell align="center">
                      {departmentReport.numberOfRejectedCandidates}
                    </TableCell>
                  </TableRow>
                </TableBody>
              </Table>
            </TableContainer>
          </div>
        </div>
      )}
    </div>
  );
};

export default DepartmentReport;
