# Android CandleStick

#### 프로젝트 개요

<a herf="https://github.com/PhilJay/MPAndroidChart">MPAndroidChart</a>

안드로이드용 차트를 구현해주는 오픈소스 중 가장 사용자가 많은 MPAndroidChart 중 CandleStick 부분을 조금 더 신경 써서 필요한 정보(캔들 시간, 가격, 고점 및 저점 부분)등을 추가하고, 정적이 아닌 동적인 뷰를 추가하는 뷰를 구현하는 CandleStick이다.



#### 프로젝트 참여 인원

현재 1인 프로젝트

참여자 : <a herf="https://github.com/MinJunsu">민준수</a>



#### 프로젝트의 목표 및 배경

##### 목표

1. X축과 Y축에 캔들의 시간과 가격을 모두 담고, 캔들 하나를 클릭 하였을 때, 그 캔들에 대한 가격정보가 나올 수 있도록 한다.
2. 입력한 데이터의 한 순간만 나오는 캔들이 아닌 뷰를 움직였을 때, 동적으로 캔들스틱을 움직이며 새로운 뷰가 나오도록 한다.
3. 처음으로 시작하는 오픈소스 프로젝트지만 최소 3명 이상이 관심을 가지고 기여를 해줄 수 있도록 유의미한 작업을 한다.

##### 배경

안드로이드를 사용한 주식 혹은 가상화폐 같은 트레이딩 앱을 구현하려고 할 때, 우리는 차트를 대부분 사용한다. 특히 주식 분석과 같은 경우 일반적으로 캔들스틱(CandleStick)이라는 차트를 이용한다. 안드로이드용 차트를 구현시켜주는 오픈소스는 여럿 존재하지만, 가장 유명한 오픈소스 MPChart를 사용해 본 결과 캔들스틱을 잘 구현하긴 하지만, 가격이 나오는 부분 혹은 안드로이드의 장점인 유연한 뷰가 안된다는 단점이 존재한다. 그러한 이유로 개인적인 공부 및 내가 원하는 부분이 다른 누군가도 필요할 수 있다는 생각에 Android 전용 유연한 그리고 필요한 정보가 모두 담겨있는 캔들스틱을 제작해야겠다는 생각을 하고 이 프로젝트를 시작한다.



#### 프로젝트 진행 방법

이 프로젝트의 프로토타입을 만들기 위해 4단계를 걸쳐 작업을 할 것이다.

1. 우선 캔들스틱 뷰를 할 수 있도록 안드로이드의 onDraw메서드를 사용하여 정적인 캔들스틱 뷰를 만들 것이다. (2020.12.03 13:00 캔들 나오는 것은 완성)
2. 정적인 캔들스틱 뷰를 만든 다음 그 정적 뷰를 한번에 볼 수 있도록 X축에
   시간과 Y축에 가격을 표기하는 메서드를 추가할 것이다.(2020.12.14 13:00
   정상적인 캔들 보조선이 나오게 완성)
3. 조금은 어려울 것 같은 부분이지만, BItmap 클래스를 사용하여 왼쪽으로
   드래그할 시에 새로운 캔들스틱이 그려지고 지금까지 그렸던 캔들스틱은
   사라지도록 할 것이다. (2020.12.16 드래그로 좌표를 처리하도록 하는 코드
   작성)



#### 프로젝트의 참여 방법 안내
우선 이 프로젝트는 현재까지 완성한 부분을 마지막으로 12월에는 끝마칠 것이다.
하지만 완전히 끝난 것이 아닌 계속 추가할 예정이므로 참여 방법을 정리할
예정이다.
1. CandleStick의 View를 동적으로 만들기 위해 Bitmap클래스를 추가하는 것
2. Bitmap이 추가가 되었다면 Zoom In / Out 기능을 추가하여 캔들 사이즈를
   조절할 수 있는 것
3. 마지막으로 CandleStick 데이터를 어떻게 불러올 것 인가 이다.

혹시 관심있는 다른 누군가가 이걸 보게 된다면 PullRequest를 통해 참여해주시면
감사하겠습니다.

