<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <style>
        /* General Reset */
        body, table, td, a {
            -webkit-text-size-adjust: 100%;
            -ms-text-size-adjust: 100%;
        }
        table, td {
            mso-table-lspace: 0pt;
            mso-table-rspace: 0pt;
        }
        img {
            -ms-interpolation-mode: bicubic;
        }
        img, table {
            border: 0;
            outline: none;
            text-decoration: none;
        }
        body {
            margin: 0;
            padding: 0;
            width: 100% !important;
            height: 100% !important;
            background-color: #f4f4f4;
        }
        table {
            border-collapse: collapse !important;
        }

        /* Responsive Styles */
        @media screen and (max-width: 600px) {
            .main-container {
                width: 100% !important;
            }
            .logo {
                font-size: 20px !important;
                padding: 15px !important;
            }
            h1 {
                font-size: 22px !important;
                text-align: center !important;
            }
            p, ul, li, td {
                font-size: 16px !important;
            }
            .order-summary table {
                width: 100% !important;
            }
            .footer p {
                text-align: center !important;
                font-size: 14px !important;
            }
        }

        /* Logo and Navigation Bar */
        .logo-nav {
            background-color: #24252A;
            padding: 20px;
            text-align: center;
        }
        .logo {
            font-family: 'Montserrat', sans-serif;
            color: #ffffff;
            font-size: 24px;
            font-weight: bold;
            background: linear-gradient(135deg, #590b74 35%, #298096 100%);
            padding: 10px;
            border-radius: 5px;
            display: inline-block;
        }

        /* Content Styles */
        .main-container {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
        }
        .content {
            font-family: Arial, sans-serif;
            color: #333333;
            line-height: 1.6;
        }
        h1 {
            font-size: 26px;
            color: #333333;
            margin-bottom: 20px;
        }
        p {
            margin-bottom: 20px;
        }

        /* Order Summary */
        .order-summary h3 {
            font-size: 20px;
            margin-bottom: 15px;
            color: #444444;
        }
        .order-summary table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .order-summary th, .order-summary td {
            padding: 10px;
            border-bottom: 1px solid #dddddd;
            font-size: 16px;
        }
        .order-summary ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        /* Footer */
        .footer {
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #dddddd;
        }
        .footer p {
            font-size: 14px;
            color: #777777;
            text-align: center;
            margin: 0;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Order Confirmation</title>
</head>
<body>
<div class="logo-nav">
    <div class="logo">DripClothe</div>
</div>

<div class="main-container">
    <div class="content">
        <h1>Thank You for Your Order!</h1>
        <p>Dear ${order.customer.getLastName()}, ${order.customer.getFirstName()},</p>
        <p>Thank you for shopping with DripClothe! We are pleased to confirm your order and will keep you updated on its status.</p>
        <p>Below is a summary of your purchase:</p>

        <div class="order-summary">
            <h3>Order Summary:</h3>
            <table>
                <#list order.getOrderItems() as orderItem>
                    <tr>
                        <td>${orderItem.getProductName()}</td>
                        <td>
                            <ul>
                                <li>Size: ${orderItem.getSize()}</li>
                                <li>Color: ${orderItem.getColor()}</li>
                                <li>Quantity: ${orderItem.getQuantity()}</li>
                            </ul>
                        </td>
                        <td>${orderItem.getPrice()}$</td>
                    </tr>
                </#list>
            </table>

            <table>
                <tr>
                    <th>Search and service fees:</th>
                    <td>2.0$</td>
                </tr>
                <tr>
                    <th>Shipping Fee:</th>
                    <td>${order.getOrder().getShippingFee()}$</td>
                </tr>
                <tr>
                    <th>Total Paid:</th>
                    <td>${order.getOrder().getTotalPrice()}$</td>
                </tr>
            </table>
        </div>

        <p>We hope you enjoy your purchase! If you have any questions, feel free to contact our support team.</p>
        <p>Best regards,<br>DripClothe Team</p>
    </div>

    <div class="footer">
        <p>&copy; 2024 DripClothe. All rights reserved.</p>
    </div>
</div>
</body>
</html>
