package com.example.openningscreencompose.ui.screens.home_user.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openningscreencompose.R


//FakeData
val list = listOf(
    Appointment(
        id = "1",
        image = R.drawable.calendar,
        name = "Khám tổng quát"
    ),
    Appointment(
        id = "2",
        image = R.drawable.ai,
        name = "Khám nha khoa"
    ),
    Appointment(
        id = "3",
        image = R.drawable.profile,
        name = "Tiêm phòng Vaccine"
    ),
    Appointment(
        id = "4",
        image = R.drawable.calendar,
        name = "Khám tổng quát"
    ),
    Appointment(
        id = "5",
        image = R.drawable.ai,
        name = "Khám nha khoa"
    ),
    Appointment(
        id = "6",
        image = R.drawable.profile,
        name = "Tiêm phòng Vaccine"
    ),
    Appointment(
        id = "7",
        image = R.drawable.calendar,
        name = "Khám tổng quát"
    ),
    Appointment(
        id = "8",
        image = R.drawable.ai,
        name = "Khám nha khoa"
    ),
    Appointment(
        id = "9",
        image = R.drawable.profile,
        name = "Tiêm phòng Vaccine"
    )

)


@Composable
fun BookScreen(
    appointmentList: List<Appointment> = list
) {
    // Khung chứa thay recycleview trong xml
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Thay cho diffUntil trong xml
        items(
            items = appointmentList,
            key = {
                appointment -> appointment.id
            },
            contentType = {
                "appointment_type"
            }
        ) { appointment ->
            AppointmentItem(appointment = appointment)
        }
    }
}


//Danh sách cụ thể để nhét vào khung chứa
@Composable
fun AppointmentItem(appointment: Appointment) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = appointment.image),
                contentDescription = "Hình ảnh dịch vụ",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = appointment.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}