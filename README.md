# Brewkery ☕🥐

A modern native Android coffee and bakery ordering application built with **Kotlin** and **Jetpack Compose**. The app allows users to browse menu items, view detailed product information, add items to a cart, and place an order.

The application is powered by the REST APIs provided as part of the Brewkery assignment.

---

## 📱 Features

- Browse the Brewkery menu
- Fetch menu data from a REST API
- View individual product details
- Fetch detailed item information using the single-item API
- Add items to the shopping cart
- View cart items and order summary
- Calculate subtotal, service charge, and total
- Place an order and view an order confirmation screen
- Classic coffee and bakery-inspired user interface
- Loading and error states for API requests

---

## 📸 App Flow

```text
Menu
  ↓
Product Details
  ↓
Add to Cart
  ↓
Cart
  ↓
Place Order
  ↓
Order Confirmation
```

---

## 🏗️ Architecture

The application follows a simple MVVM-based architecture with a Repository layer.

```text
Jetpack Compose UI
        ↓
     ViewModel
        ↓
    Repository
        ↓
 Retrofit / REST API
```

### Project Structure

```text
com.example.brewkeryapp
│
├── data
│   ├── model
│   │   ├── MenuItem
│   │   ├── BrewkeryResponse
│   │   └── Other API models
│   │
│   ├── remote
│   │   └── BrewkeryApi
│   │
│   └── repository
│       └── BrewkeryRepository
│
├── screen
│   ├── menu
│   │   └── MenuScreen
│   ├── detail
│   │   └── DetailScreen
│   ├── cart
│   │   └── CartScreen
│   └── order
│       └── OrderScreen
│
├── viewmodel
│   ├── MenuViewModel
│   └── DetailViewModel
│
└── MainActivity
```

---

## 🌐 REST API Integration

The app uses the REST API provided with the Brewkery assignment.

### Full Menu API

Used to fetch the complete list of menu items:

```text
data.json
```

### Single Item API

Used to fetch detailed information for a selected menu item:

```text
api/items/{id}.json
```

The API integration is implemented using **Retrofit**.

---

## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Retrofit**
- **Kotlin Coroutines**
- **ViewModel**
- **MVVM Architecture**
- **Repository Pattern**
- **REST API**

---

## ▶️ How to Run the Project

### 1. Clone the repository

Clone this repository to your local machine.

### 2. Open the project

Open the project in **Android Studio**.

### 3. Sync Gradle

Allow Android Studio to download the required dependencies and complete the Gradle sync.

### 4. Run the application

Connect an Android device or start an Android Emulator.

Then click **Run ▶** in Android Studio.

> **Note:** An internet connection is required to load menu and product details from the REST API.

---

## 🤖 AI Tools Used

AI tools were used as part of the development process for implementation assistance, UI improvements, debugging, and project structuring.

### AI Tool

- **ChatGPT**

### Examples of prompts used

- "Help me structure a native Android application using Kotlin, Jetpack Compose, MVVM, and a Repository layer."
- "Help me integrate the Brewkery REST API using Retrofit."
- "Help me create a MenuViewModel and DetailViewModel for API-driven data."
- "Help me update MainActivity so the selected item ID is passed to the DetailScreen."
- "Help me design a polished classic bakery and coffee shop UI using Jetpack Compose."
- "Help me improve the Menu, Detail, Cart, and Order screens while preserving the existing API and cart functionality."
- "Help me identify and fix issues with the DetailScreen navigation and API integration."

AI-generated suggestions were reviewed and adapted during development and testing.

---

## 🐛 Bug Caught and Fixed

### Issue

After updating the Detail Screen to fetch individual product information from the REST API, the screen interface changed to require an `itemId`.

However, `MainActivity` was still using the previous navigation implementation and was passing the complete item differently.

### Fix

`MainActivity` was updated to pass the selected item's ID:

```kotlin
DetailScreen(
    itemId = item.id,
    onAddToCart = { itemFromApi ->
        cartItems.add(itemFromApi)
        currentScreen = "cart"
    },
    onBack = {
        currentScreen = "menu"
    }
)
```

This allowed the `DetailViewModel` to correctly fetch the selected product using the single-item REST API.

---

## 🧪 Testing

The following flow was tested:

```text
Launch App
    ↓
Load Menu from API
    ↓
Select Menu Item
    ↓
Load Item Details from API
    ↓
Add Item to Cart
    ↓
View Cart and Order Summary
    ↓
Place Order
    ↓
View Order Confirmation
```

---

## 📦 APK

A debug APK is included separately with the submission so the application can be installed and tested directly on an Android device.

---

## ✨ Assignment Notes

This project was developed for the **Brewkery Android Developer Assignment**.

The focus was on:

- Clean and simple architecture
- REST API integration
- Functional four-screen user flow
- A polished native Android UI
- Avoiding unnecessary over-engineering

---

### Thank you for reviewing the project! ☕🥐
