// import  "./DepartmentReport.css";
// import { Form } from "react-bootstrap";
// import { PieChart, Pie, Sector, Cell, ResponsiveContainer } from 'recharts';


// const data = [
//     { name: 'Group A', value: 400 },
//     { name: 'Group B', value: 300 },
//     { name: 'Group C', value: 300 },
//     { name: 'Group D', value: 200 },
//   ];
  
//   const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];
  
//   const RADIAN = Math.PI / 180;
//   const renderCustomizedLabel = ({ cx, cy, midAngle, innerRadius, outerRadius, percent, index }) => {
//     const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
//     const x = cx + radius * Math.cos(-midAngle * RADIAN);
//     const y = cy + radius * Math.sin(-midAngle * RADIAN);
  
//     return (
//       <text x={x} y={y} fill="white" textAnchor={x > cx ? 'start' : 'end'} dominantBaseline="central">
//         {`${(percent * 100).toFixed(0)}%`}
//       </text>
//     );
//   };

// const ChartDepartmentReport = () => {
  
//   return (
//     <div>
//         1
//         <ResponsiveContainer width="100%" height="100%">
//         <PieChart width={400} height={400}>
//           <Pie
//             data={data}
//             cx="50%"
//             cy="50%"
//             labelLine={false}
//             label={renderCustomizedLabel}
//             outerRadius={80}
//             fill="#8884d8"
//             dataKey="value"
//           >
//             {data.map((entry, index) => (
//               <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
//             ))}
//           </Pie>
//         </PieChart>
//       </ResponsiveContainer>
//     </div>
//   );
// };

// export default ChartDepartmentReport;


import React, { Component } from 'react';
import CanvasJSReact from '@canvasjs/react-charts';
import DepartmentReport from './DepartmentReport';
//var CanvasJSReact = require('@canvasjs/react-charts');
 
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;
class ChartDepartmentReport extends Component {

    generateRandomColors = (numColors) => {
        const colors = [];
        for (let i = 0; i < numColors; i++) {
          const randomColor = `#${Math.floor(Math.random()*16777215).toString(16)}`;
          colors.push(randomColor);
        }
        return colors;
      };

	render() {
        const {data, title} = this.props;

        const COLORS = this.generateRandomColors(data.length);

        console.log("report",data)
		const options = {
			theme: "dark2",
			animationEnabled: true,
			exportFileName: title,
			exportEnabled: true,
			title:{
				text: title,
                // fontColor: "rgb(20, 20, 20)"
			},
            backgroundColor: "rgb(112, 112, 111)", // Màu nền của biểu đồ
            
			data: [{
				type: "pie",
				showInLegend: true,
				legendText: "{label}",
				toolTipContent: "{label}: <strong>{y}%</strong>",
				indexLabel: "{y}%",
				indexLabelPlacement: "inside",
				dataPoints: data.map((entry, index) => ({
          ...entry,
          color: COLORS[index],
          fontColor: "rgb(20, 20, 20)"
        })),
			}]
		}
		return (
            <div>
            <CanvasJSChart options={options} />
          </div>
		);
	}
}
export default ChartDepartmentReport;              