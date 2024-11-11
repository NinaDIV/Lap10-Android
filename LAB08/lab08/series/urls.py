from django.urls import path

from . import views

urlpatterns = [
    path('',views.IndexView.as_view(),name='index'),
    path('serie/',views.SeriesView.as_view(),name='series'),
    path('serie/<int:pk>/', views.SerieDetailView.as_view(), name='serie-detail')
    
]
