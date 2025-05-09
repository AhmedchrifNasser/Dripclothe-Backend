<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Track Your Order</title>
    <style>
        body {
            font-family: "Montserrat", sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
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
        .email-container {
            background-color: #ffffff;
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .header {
            text-align: center;
            color: #590b74;
            font-size: 1.8rem;
            font-weight: bold;
        }
        .content {
            margin-top: 20px;
            font-size: 1rem;
            line-height: 1.6;
            color: #333;
        }
        .content p {
            margin: 10px 0;
        }
        .content span {
            font-weight: bold;
            color: #298096;
        }
        .order-details {
            margin-top: 20px;
            padding: 15px;
            border: 1px solid #eee;
            background-color: #f9f9f9;
            border-radius: 8px;
        }
        .order-details p {
            margin: 5px 0;
            font-size: 0.95rem;
            color: #555;
        }
        .button-container {
            text-align: center;
            margin-top: 30px;
        }
        .button {
            background: linear-gradient(135deg, #590b74 35%, #298096 100%);
            padding: 1rem;
            margin-top: 5%;
            border: none;
            border-radius: 50px;
            color: white !important;
            font-weight: 500;
            font-size: 1.2rem;
            letter-spacing: .11rem;
            outline: none;
            cursor: pointer;
            text-decoration: none;
            font-family: "Montserrat", sans-serif;
        }
        .footer {
            text-align: center;
            margin-top: 40px;
            font-size: 0.9rem;
            color: #aaa;
        }
    </style>
</head>
<body>
<div class="logo-nav">
    <div class="logo">DripClothe</div>
</div>
<div class="email-container">
    <div class="header">
        Your Order is on its Way!
    </div>
    <div class="content">
        <p>Dear ${order.customer.getLastName()},</p>
        <p>Thank you for shopping with us! Here's an update on your order:</p>
        <div class="order-details">
            <p><strong>Order Number:</strong> ${order.getOrder().getOrderTrackingNumber()}</p>
            <p><strong>Status:</strong> Preparing for Shipment</p>
        </div>
        <p>You can track your order by clicking the button below:</p>
    </div>
    <div class="button-container">
        <a href="http://localhost:4200/Track_Status/${order.getOrder().getOrderTrackingNumber()}" class="button">
            Track Your Order
        </a>
    </div>
    <div class="footer">
        <p>If you have any questions, feel free to contact our <a href="http://localhost:4200/contact" style="color: #590b74;">support team</a>.</p>
        <p>Thank you for choosing us!</p>
    </div>
</div>
</body>
</html>
