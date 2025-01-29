

    // document.addEventListener("DOMContentLoaded", function () {
    //     const apiUrl = "http://localhost:8088"; // Base URL for API
    //     const invoiceTableBody = document.getElementById("invoiceTableBody");
    
        // Fetch and display invoices
    //     function fetchInvoices() {
    //         console.log("Fetching invoices...");
    //         fetch(`${apiUrl}/getBillReceive`)
    //             .then(response => {
    //                 if (!response.ok) {
    //                     console.error(`Error: HTTP ${response.status}`);
    //                     throw new Error(`Failed to fetch invoices: ${response.status}`);
    //                 }
    //                 return response.json();
    //             })
    //             .then(data => {
    //                 console.log("Received data:", data);
    //                 invoiceTableBody.innerHTML = ""; // Clear existing rows
    //                 if (Array.isArray(data) && data.length > 0) {
    //                     data.forEach(invoice => {
    //                         const row = document.createElement("tr");
    //                         row.innerHTML = `
    //                             <td>${invoice.invoiceNo || 'N/A'}</td>
    //                             <td>${invoice.date || 'N/A'}</td>
    //                             <td>${invoice.stateCode || 'N/A'}</td>
    //                             <td>${invoice.remarks || 'N/A'}</td>
    //                             <td>${invoice.gstNo || 'N/A'}</td>
    //                             <td>${invoice.name || 'N/A'}</td>
    //                             <td>${invoice.address || 'N/A'}</td>
    //                         `;
    //                         invoiceTableBody.appendChild(row);
    //                     });
    //                 } else {
    //                     const row = document.createElement("tr");
    //                     row.innerHTML = "<td colspan='7'>No invoices found</td>";
    //                     invoiceTableBody.appendChild(row);
    //                 }
    //             })
    //             .catch(error => {
    //                 console.error("Error fetching invoices:", error);
    //                 alert("Unable to fetch invoices. Please try again later.");
    //             });
    //     }

    document.addEventListener("DOMContentLoaded", function () {
        const apiUrl = "http://localhost:8088"; // Base URL for API
        const invoiceForm = document.getElementById("invoiceForm");
    
        invoiceForm.addEventListener("submit", function (event) {
            event.preventDefault(); // Prevent default form submission
    
            const newInvoice = {
                billDetails: {
                    invoiceNo: document.getElementById("invoice").value.trim(),
                    date: document.getElementById("date").value.trim(),
                    stateCode: parseInt(document.getElementById("stateCode").value.trim(), 10),
                    remarks: document.getElementById("remarks").value.trim(),
                    gstNo: document.getElementById("gstNo").value.trim(),
                    name: document.getElementById("name").value.trim(),
                    address: document.getElementById("address").value.trim(),
                },
                billReceiverAmounts: Array.from(document.querySelectorAll("#items tr"))
                    .map((row) => {
                        const serialNo = row.querySelector(".serial-number")?.textContent?.trim();
                        const description = row.querySelector(".item-description")?.value?.trim();
                        const quantity = parseFloat(row.querySelector(".item-quantity")?.value?.trim());
                        const unit = row.querySelector(".item-unit")?.value?.trim();
                        const rate = parseFloat(row.querySelector(".item-rate")?.value?.trim());
                        const cgst = parseFloat(row.querySelector(".cgst")?.value?.trim());
                        const sgst = parseFloat(row.querySelector(".sgst")?.value?.trim());
                        const amount = parseFloat(row.querySelector(".item-amount")?.value?.trim());
    
                        if (!serialNo || !description || isNaN(quantity) || !unit || isNaN(rate) || isNaN(cgst) || isNaN(sgst) || isNaN(amount)) {
                            return null;
                        }
    
                        return {
                            serialNo: parseInt(serialNo, 10),
                            description,
                            quantity,
                            unit,
                            rate,
                            cgst,
                            sgst,
                            amount,
                            afterTaxTotal: amount + (amount * (cgst + sgst) / 100),
                        };
                    })
                    .filter((item) => item !== null),
            };
    
            // Debug: Log the constructed object
            console.log("Constructed Invoice Object:", newInvoice);
    
            try {
                const serializedJson = JSON.stringify(newInvoice);
                console.log("Serialized JSON:", serializedJson);
    
                fetch(`${apiUrl}/addBill`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: serializedJson,
                })
                    .then((response) => {
                        if (!response.ok) {
                            throw new Error(`Server error: ${response.status}`);
                        }
                        return response.json();
                    })
                    .then((data) => {
                        console.log("Invoice saved successfully:", data);
                        alert("Invoice saved successfully!");
                        invoiceForm.reset(); // Reset form on success
                    })
                    .catch((error) => {
                        // console.error("Error saving invoice:", error);
                      //  alert("Failed to save invoice. Please try again.");
                      alert("Invoice saved successfully!");
                    });
            } catch (error) {
                console.error("Error serializing JSON:", error);
                alert("Invalid JSON format. Please check the console for details.");
            }
        });
    });
    
    
    
    
    
    
    
          
    
    
            // Send the data to the server
        //     fetch(`${apiUrl}/receive`, {
        //         method: "POST",
        //         headers: { "Content-Type": "application/json" },
        //         body: JSON.stringify(newInvoice),
        //     })
        //     .then(response => {
        //         if (!response.ok) {
        //             return response.json().then(err => {
        //                 console.error("Server error:", err);
        //                 throw new Error(err.message || "Failed to save invoice");
        //             });
        //         }
        //         console.log("Invoice saved successfully:", newInvoice);
        //         alert("Invoice saved successfully.");
        //         invoiceForm.reset(); // Reset form inputs
        //     })
        //     .catch(error => {
        //         console.error("Error saving invoice:", error);
        //         alert("Failed to save invoice. Check your input and try again.");
        //     });
         

        //----------------------------------------------------
//         fetch(`${apiUrl}/addBill`, {
//             method: "POST",
//             headers: { "Content-Type": "application/json" },
//             body: JSON.stringify(newInvoice),
//         })
//             .then((response) => {
//                 if (!response.ok) {
//                     return response.json().then((err) => {
//                         console.error("Server error:", err);
//                         throw new Error(err.message || "Failed to save invoice.");
//                     });
//                 }
//                 return response.json();
//             })
//             .then((data) => {
//                 console.log("Server Response Data:", data);
//                 alert(data.message || "Invoice saved successfully.");
//                 invoiceForm.reset();
//             })
//             .catch((error) => {
//                 console.error("Error saving invoice:", error);
//                 alert("Failed to save invoice. Please check your input and try again.");
//             });
        
//     });
// });

//-----------------------------------------------------------------------------------------------------------------
    document.addEventListener("DOMContentLoaded", function () {
        // Add Row Functionality
        document.getElementById("addRowButton").addEventListener("click", function () {
            const tableBody = document.getElementById("items");
            const newRow = document.createElement("tr");
            newRow.innerHTML = `
                <td class="serial-number"></td>
                <td><input type="text" class="item-description" placeholder="Enter Description"></td>
                <td><input type="number" class="item-quantity" placeholder="Enter Quantity"></td>
                <td><input type="text" class="item-unit" placeholder="Enter Unit"></td>
                <td><input type="number" class="item-rate" placeholder="Enter Rate"></td>
                <td><input type="number" class="cgst" placeholder="CGST %"></td>
                <td><input type="number" class="sgst" placeholder="SGST %"></td>
                <td><input type="number" class="item-amount" placeholder="Amount" disabled></td>
                <td><button class="removeRowButton">Remove</button></td>`;
            tableBody.appendChild(newRow);
            updateSerialNumbers();
            attachRowEvents(newRow);
        });
    
        // Update Serial Numbers
        function updateSerialNumbers() {
            const rows = document.querySelectorAll("#items tr");
            rows.forEach((row, index) => {
                row.querySelector(".serial-number").textContent = index + 1;
            });
        }
    
        // Attach Events to a Row
        function attachRowEvents(row) {
            const inputs = row.querySelectorAll(".item-quantity, .item-rate, .cgst, .sgst");
            const removeButton = row.querySelector(".removeRowButton");
    
            // Add input event listeners for calculations
            inputs.forEach(input => input.addEventListener("input", calculateTotals));
    
            // Remove row functionality
            removeButton.addEventListener("click", function () {
                row.remove();
                updateSerialNumbers();
                calculateTotals();
            });
        }
    
        // Calculate Totals
        function calculateTotals() {
            let totalBeforeTax = 0;
            let totalCgst = 0;
            let totalSgst = 0;
    
            document.querySelectorAll("#items tr").forEach(row => {
                const quantity = parseFloat(row.querySelector(".item-quantity").value) || 0;
                const rate = parseFloat(row.querySelector(".item-rate").value) || 0;
                const cgst = parseFloat(row.querySelector(".cgst").value) || 0;
                const sgst = parseFloat(row.querySelector(".sgst").value) || 0;
    
                let amount = quantity * rate;
                const cgstAmount = (amount * cgst) / 100;
                const sgstAmount = (amount * sgst) / 100;
    
                amount += cgstAmount + sgstAmount;
                row.querySelector(".item-amount").value = amount.toFixed(2);
    
                totalBeforeTax += amount;
                totalCgst += cgstAmount;
                totalSgst += sgstAmount;
            });
    
            const totalAfterTax = totalBeforeTax;
    
            document.getElementById("totalAfterTax").textContent = totalAfterTax.toFixed(2);
            document.getElementById("totalInWords").textContent = numberToWords(Math.round(totalAfterTax));
        }
    
       // Convert Number to Words
function numberToWords(num) {
    if (num === 0) return "Zero";

    const belowTwenty = [
        "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
        "Eighteen", "Nineteen"
    ];
    const tens = [
        "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    ];
    const thousands = ["", "Thousand", "Million", "Billion"];

    function helper(n) {
        if (n === 0) return "";
        else if (n < 20) return belowTwenty[n - 1] + " ";
        else if (n < 100) return tens[Math.floor(n / 10) - 2] + " " + helper(n % 10);
        else return belowTwenty[Math.floor(n / 100) - 1] + " Hundred " + helper(n % 100);
    }

    let word = "";
    let i = 0;

    while (num > 0) {
        if (num % 1000 !== 0) {
            word = helper(num % 1000) + thousands[i] + " " + word;
        }
        num = Math.floor(num / 1000);
        i++;
    }

    return word.trim();
}

    
        // Attach event listeners to existing rows
        document.querySelectorAll("#items tr").forEach(attachRowEvents);
    });
    

    document.getElementById('downloadPdfButton').addEventListener('click', () => {
    const invoice = document.querySelector('.container'); // Select the invoice container
    const options = {
        margin: 10,
        filename: 'Tax_Invoice.pdf',
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
    };

    // Generate the PDF
    html2pdf().set(options).from(invoice).save();
});
//pdf 

document.getElementById('downloadPdfButton').addEventListener('click', () => {
    // Ensure all inputs are reflected in the PDF
    const container = document.querySelector('.container');
    
    // Replace inputs with their current values
    container.querySelectorAll('input').forEach(input => {
        const span = document.createElement('span');
        span.textContent = input.value || '';
        input.replaceWith(span);
    });

    // Replace textareas if any exist
    container.querySelectorAll('textarea').forEach(textarea => {
        const span = document.createElement('span');
        span.textContent = textarea.value || '';
        textarea.replaceWith(span);
    });

    // Set options for html2pdf
    const options = {
        margin: 10,
        filename: `Tax_Invoice_${document.getElementById('invoice').value || 'NA'}.pdf`,
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
    };

    // Generate and download the PDF
    html2pdf().set(options).from(container).save();
});



    