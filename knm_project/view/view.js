

document.addEventListener("DOMContentLoaded", function () {
    const apiUrl = "http://localhost:8088"; // Base URL for API
    const invoiceTableBody = document.getElementById("invoiceTableBody");

    // Fetch and display invoices
    function fetchInvoices() {
        console.log("Fetching invoices...");
        fetch(`${apiUrl}/getBillReceive`)
            .then(response => {
                console.log("Response Status:", response.status); // Log the status of the response
                if (!response.ok) {
                    console.error(`Error: HTTP ${response.status}`);
                    throw new Error(`Failed to fetch invoices: ${response.status}`);
                }
                return response.text();  // Read response as text to debug
            })
            .then(text => {
                console.log("Raw Response Text:", text); // Log the raw response text
                try {
                    const data = JSON.parse(text); // Parse the text into JSON
                    console.log("Parsed JSON Data:", data); // Log the parsed JSON data

                    invoiceTableBody.innerHTML = ""; // Clear existing rows
                    if (Array.isArray(data) && data.length > 0) {
                        data.forEach(invoice => {
                            const row = document.createElement("tr");
                            row.innerHTML = `
                                <td>${invoice.invoiceNo || 'N/A'}</td>
                                <td>${invoice.date || 'N/A'}</td>
                                <td>${invoice.stateCode || 'N/A'}</td>
                                <td>${invoice.remarks || 'N/A'}</td>
                                <td>${invoice.gstNo || 'N/A'}</td>
                                <td>${invoice.name || 'N/A'}</td>
                                <td>${invoice.address || 'N/A'}</td>
                            `;
                            invoiceTableBody.appendChild(row);
                        });
                    } else {
                        const row = document.createElement("tr");
                        row.innerHTML = "<td colspan='7'>No invoices found</td>";
                        invoiceTableBody.appendChild(row);
                    }
                } catch (error) {
                    console.error("Error parsing JSON:", error);
                    alert("Failed to parse response data. Please check the console for details.");
                }
            })
            .catch(error => {
                console.error("Error fetching invoices:", error);
                alert("Unable to fetch invoices. Please try again later.");
            });
    }

    fetchInvoices();
});



// Handle form submission to save a new invoice
// invoiceForm.addEventListener("submit", function (event) {
//     event.preventDefault(); // Prevent form default submission

//     const newInvoice = {
//         invoiceNo: document.getElementById("invoice").value,
//         date: document.getElementById("date").value,
//         stateCode: document.getElementById("stateCode").value,
//         remarks: document.getElementById("remarks").value,
//         gstNo: document.getElementById("gstNo").value,
//         name: document.getElementById("name").value,
//         address: document.getElementById("address").value,
//     };
    

    // Basic validation
    // if (!newInvoice.invoiceNo || !newInvoice.date || !newInvoice.gstNo || !newInvoice.name) {
    //     alert("Please fill in all required fields.");
    //     return;
    // }

    
        
//});

//  Initial load: Fetch invoices



