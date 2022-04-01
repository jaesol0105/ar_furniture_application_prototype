# 개발일지
우리 팀이 개발하고자 하는 'AR을 동한 가구 배치 시뮬레이션' 어플리케이션에는 크게 두 가지 핵심적인 기능이 있다.

1. AR을 통한 가구를 배치하는 기능
2. 딥러닝을 통하여 실내 공간의 무드에 맞는 가구를 추천해주는 부가 기능

팀원의 역할분담은 다음과 같다.

|김진원 & 권준혁|이재솔 & 최정인|
|---|---|
|AR Foundation을 이용하여 AR 기능을 구현|실내 공간의 무드에 맞는 가구를 추천해주는 CNN 모델을 구축|

# 2022-02-24 ~
### 데이터 셋 수집

모델 구축에 앞서, 모델 학습 및 평가에 사용할 데이터 셋을 수집하였다. 인테리어 디자인에 대한 기존 데이터 셋을 여러 사이트에서 찾아보았지만 원하는 데이터 셋 자료를 찾을 수 없었다. 따라서 크롤링을 통해 데이터 셋으로 사용될 이미지들을 직접 수집하였다.

<a href="https://www.youtube.com/watch?v=1b7pXC1-IbE">크롤링에는 Python과, 프레임워크 Selenium을 이용하였다.</a>
 
### CNN 모델 prototype 구축

  모델 구축의 절차는 크게 아래와 같다.

  - 데이터 셋 불러오기
  - Sequential 모델 구축
  - 모델 컴파일 및 학습

  우선 구글 Colab을 통하여, 불러온 이미지들을 이미지 전처리 작업 후 커스텀 데이터 셋을 만들었다.
  다음 예제를 참고하였다. https://lhh3520.tistory.com/376

  모델 구축시 초기에 사용한 분류 카테고리는 대표적인 인테리어 디자인의 범주인 (‘모던 인테리어’, ‘북유럽풍 인테리어’, ‘네추럴 인테리어’) 등과 같이 설정하였다.

  Tensorflow 라이브러리를 통하여 CNN 모델을 구축하고 평가해 보았지만 분류 정확도가 매우 낮은 문제점이 발생하였다. 이는 모델 파라메터 문제가 아닌 '인테리어 디자인' 자체의 분류 기준이 난해하기 때문이다. 대부분의 인테리어 디자인 이미지들은 명확한 컨셉을 가진 경우보다 여러 인테리어 스타일들이 조금씩 융합된 경우가 많다. 이에 대한 해결방안으로는 분류 기준을 좀 더 넓고 포괄적으로 잡되 명확한 특징이 있게 잡아서 분류 정확도를 높여 보려 한다.

  일단 분류 정확도만이라도 일정 수준 이상으로 올리기 위하여 실내 공간의 사진을 input으로 크게 두 가지의 카테고리로 분류하는 모델을 구축하였다.
  
  블랙 앤 화이트 계통을 메인으로 심플하고 현대적인 디자인인 <b>'모던&미니멀'</b> 인테리어와, 목재등의 자연소재들을 적극적으로 활용한 <b>'네추럴 인테리어'</b>로 카테고리를 나누었다.

  구축된 모델의 분류 정확도는 vaild dataset 기준 70%정도로, 이전 모델보다 유의미한 정확도를 보였다. 하지만 복합적인 요소로 이루어진 '인테리어 디자인'을 분류하기 위해서는 단순하게 하이퍼 파라메터의 조정할 뿐이 아니라, 명확한 분류기준을 설정할 필요가 있어보인다. 이 부분을 해결하기 위해서는 딥러닝에 대한 좀 더 깊은 공부가 필요할 것 같다.
<br>

|김진원|이재솔|최정인|권준혁|
|---|---|---|---|
|AR 기능 구현|어플리케이션 통합|CNN 모델 구축|UI 설계|

이후 역할 분담을 재조정하여, 최정인 팀원이 혼자 CNN 모델 구축을 맡게되었다.<br>
나는 유니티 프로젝트(AR)와 CNN 분류 모델을 안드로이드 스튜디오를 통해 하나의 어플로 통합하고, 어플리케이션 편의성 기능들을 구현하는 역할을 맡게되었다.



# 2022-03-20
앞서 구축했던 CNN 모델의 prototype (분류 정확도는 높지 않지만..)을, 안드로이드 어플리케이션에 연동하였다.

<a href="https://cppmagister.tistory.com/5">CNN 모델을 tflite 형태로 변환</a>하고, <a href="https://www.youtube.com/watch?v=jhGm4KDafKU">안드로이드 스튜디오 프로젝트에 추가하였다.</a>

인텐트를 통해 카메라로부터 이미지를 받고, 해당 이미지를 추가한 tflite 모델을 통해 분류한다.


# 2022-03-21
### AR Foundation 프로젝트 연동
유니티를 통하여 개발한 AR Foundation 프로젝트를 안드로이드 스튜디오에 연결하였다.<br>
최신버전 안드로이드 스튜디오(4.0이상) 환경에서의 유니티 프로젝트 연결에 대한 공식문서나 예제 등을 찾기 힘들어서, 생각보다 많은 여러가지 에러들과 부딪혔다. 다음 예제가 많은 도움이 되었다. <b>https://brtech.tistory.com/150</b>

추가적으로 발생한 에러들과 그 해결은 아래에 기술 하였다.

- 빌드 에러 (해결)

```
execution failed for task ':unitylibrary:buildil2cpptask'. > ndk is not installed.
```

1) 유니티 프로젝트와 안드로이드 스튜디오에서 동일한 ndk를 사용하여야 한다.
2) local.properties에 ndk.dir를 설정한다. (해결)
3) ndk 21버전 이상에서 문제가 발생한다는 게시글이 많았기 때문에 ndk-r19를 사용하였다.

<br>

- 어플 실행후 AR foundation기능을 실행하면 unity로고가 2초간 보이고, 유니티 프로젝트가 실행되는 순간 앱이 종료되는 문제 (해결)

```
E/DynamiteClient(19046): java.lang.UnsatisfiedLinkError: dlopen failed: "/data/app/~~u_1pv7mhEcdnnbSpbvHKgQ==/com.google.ar.core-ln9aByaqxbuIVWB73BtlpA==/base.apk!/lib/arm64-v8a/libarcore_c.so" [URL='http://www.google.com/search?q=is+msdn.microsoft.com']is[/URL] 64-bit instead of 32-bit
```

1) <a href="https://forum.unity.com/threads/android-12-crash-on-startup.1230936/">android 12 에서의 충돌 문제?</a><br>
sdk를 30(android 11)로 다운 하였으나 해결되지 않음.

2) 64비트 단말기에서 32비트 .so 파일을 사용해서 발생하는 문제<br>
어플리케이션은 64bit(armv8)로 수행되려는 상황인데 32bit(armv7)라이브러리만 지원되어서 앱이 정상적으로 실행되지 않는것이다.

    2-1) Unity - export project<br>
	  <b>scripting backend -> IL2CPP</b> : 64비트 활성화<br>
	  <b>ARM64 -> Check</b> : ARMv7을 선택 취소하고 ARM64를 체크<br>
    참고 : https://developer.maxst.com/BoardQuestions/Details/1232

    2-2) Android Studio - gradle<br>
    <b><a href="https://blog.naver.com/PostView.nhn?blogId=jogilsang&logNo=221605475532&categoryNo=0&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView">apk 파일 64bit 설정</a></b><br>
    ndk.abiFilters 'armeabi-v7a','arm64-v8a','x86','x86_64' 를 gradle에 추가한다


### 그 외 참고
<a href="https://ideajini.tistory.com/15">Android studio Arctic Fox 버전 이후 gradle allprojects 추가방법</a><br>
<a href="https://kadosholy.tistory.com/24">안드로이드 스튜디오 - gradle project sync failed. basic functionality (e.g. editing debugging) will not work properly 에러 해결방법</a>
